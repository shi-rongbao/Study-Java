package com.atguigu.service.impl;

import com.alibaba.csp.sentinel.util.StringUtil;
import com.alibaba.fastjson.JSON;
import com.atguigu.constant.KafkaConstant;
import com.atguigu.constant.SystemConstant;
import com.atguigu.entity.UserCollect;
import com.atguigu.entity.UserListenProcess;
import com.atguigu.mapper.TrackInfoMapper;
import com.atguigu.service.AlbumInfoService;
import com.atguigu.service.KafkaService;
import com.atguigu.service.ListenService;
import com.atguigu.service.TrackInfoService;
import com.atguigu.util.AuthContextHolder;
import com.atguigu.util.MongoUtil;
import com.atguigu.vo.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author ShiRongbao
 * @create 2024/7/23 22:00
 * @description:
 */
@Service
public class ListenServiceImpl implements ListenService {

    @Resource
    private MongoTemplate mongoTemplate;

    @Resource
    private KafkaService kafkaService;

    @Resource
    private RedisTemplate<String, Long> redisTemplate;

    @Resource
    private TrackInfoMapper trackInfoMapper;

    @Resource
    private TrackInfoService trackInfoService;

    @Resource
    private AlbumInfoService albumInfoService;

    @Override
    public void updatePlaySecond(UserListenProcessVo userListenProcessVo) {
        Long userId = AuthContextHolder.getUserId();
        // 查询当前用户的当前声音的播放记录是否在MongoDB中存在
        Query query = Query.query(Criteria.where("userId").is(userId).and("trackId").is(userListenProcessVo.getTrackId()));
        UserListenProcess userListenProcess = mongoTemplate.findOne(query, UserListenProcess.class, MongoUtil.getCollectionName(MongoUtil.MongoCollectionEnum.USER_LISTEN_PROCESS, userId));
        // 如果不存在
        if (userListenProcess == null) {
            // 把播放进度保存在mongodb中
            userListenProcess = new UserListenProcess();
            BeanUtils.copyProperties(userListenProcessVo, userListenProcess);
            userListenProcess.setUserId(userId);
            userListenProcess.setId(ObjectId.get().toString());
            userListenProcess.setIsShow(1);
            userListenProcess.setCreateTime(new Date());
        } else {
            // 如果存在，更新播放记录就可以了，更新秒数
            userListenProcess.setBreakSecond(userListenProcessVo.getBreakSecond());
        }
        userListenProcess.setUpdateTime(new Date());
        mongoTemplate.save(userListenProcess, MongoUtil.getCollectionName(MongoUtil.MongoCollectionEnum.USER_LISTEN_PROCESS, userId));
        // 更新播放次数
        // 业务要求：一个用户，一天只能让一个专辑播放量 + 1，不能重复增加播放量
        String key = "user:track:" + new DateTime().toString("yyyyMMdd") + ":" + userListenProcessVo.getTrackId();
        Boolean isExist = redisTemplate.opsForValue().getBit(key, userId);
        if (Boolean.FALSE.equals(isExist)) {
            redisTemplate.opsForValue().setBit(key, userId, true);
            redisTemplate.expire(key, 1, TimeUnit.DAYS);
            // 要发送的数据
            TrackStatMqVo trackStatMqVo = new TrackStatMqVo();
            trackStatMqVo.setBusinessNo(UUID.randomUUID().toString().replaceAll("-", ""));
            trackStatMqVo.setAlbumId(userListenProcessVo.getAlbumId());
            trackStatMqVo.setTarckId(userListenProcessVo.getTrackId());
            trackStatMqVo.setStatType(SystemConstant.PLAY_NUM_TRACK);
            trackStatMqVo.setCount(1);
            kafkaService.sendMessage(KafkaConstant.UPDATE_TRACK_STAT_QUEUE, JSON.toJSONString(trackStatMqVo));
        }

    }

    @Override
    public Map<String, Object> getRecentlyPlay() {
        Long userId = AuthContextHolder.getUserId();
        Query query = Query.query(Criteria.where("userId").is(userId));
        Sort sort = Sort.by(Sort.Direction.DESC, "updateTime");
        query.with(sort);
        UserListenProcess listenProcess = mongoTemplate.findOne(query,
                UserListenProcess.class, MongoUtil.getCollectionName(MongoUtil.MongoCollectionEnum.USER_LISTEN_PROCESS, userId));
        if (listenProcess == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("albumId", listenProcess.getAlbumId());
        map.put("trackId", listenProcess.getTrackId());
        return map;
    }

    @Override
    public BigDecimal getLastPlaySecond(Long trackId) {
        Long userId = AuthContextHolder.getUserId();
        Query query = Query.query(Criteria.where("userId").is(userId).and("trackId").is(trackId));
        UserListenProcess listenProcess = mongoTemplate.findOne(query,
                UserListenProcess.class, MongoUtil.getCollectionName(MongoUtil.MongoCollectionEnum.USER_LISTEN_PROCESS, userId));
        if (listenProcess != null) {
            return listenProcess.getBreakSecond();
        }
        return new BigDecimal(0);
    }

    @Override
    public TrackStatVo getTrackStatistics(Long trackId) {
        return trackInfoMapper.getTrackStatistics(trackId);
    }

    @Override
    public Boolean isCollect(Long trackId) {
        Long userId = AuthContextHolder.getUserId();
        Query query = Query.query(Criteria.where("userId").is(userId).and("trackId").is(trackId));
        long count = mongoTemplate.count(query, MongoUtil.getCollectionName(MongoUtil.MongoCollectionEnum.USER_COLLECT, userId));
        return count != 0;
    }

    @Override
    public boolean collectTrack(Long trackId) {
        Long userId = AuthContextHolder.getUserId();
        Query query = Query.query(Criteria.where("userId").is(userId).and("trackId").is(trackId));
        long count = mongoTemplate.count(query, MongoUtil.getCollectionName(MongoUtil.MongoCollectionEnum.USER_COLLECT, userId));
        if (count == 0) {
            UserCollect userCollect = new UserCollect();
            userCollect.setId(ObjectId.get().toString());
            userCollect.setUserId(userId);
            userCollect.setTrackId(trackId);
            userCollect.setCreateTime(new Date());
            mongoTemplate.save(userCollect, MongoUtil.getCollectionName(MongoUtil.MongoCollectionEnum.USER_COLLECT, userId));
            // 更新声音点赞量
            TrackStatMqVo trackStatMqVo = new TrackStatMqVo();
            trackStatMqVo.setBusinessNo(UUID.randomUUID().toString().replaceAll("-", ""));
            trackStatMqVo.setTarckId(trackId);
            trackStatMqVo.setStatType(SystemConstant.COLLECT_NUM_TRACK);
            trackStatMqVo.setCount(1);
            kafkaService.sendMessage(KafkaConstant.UPDATE_TRACK_STAT_QUEUE, JSON.toJSONString(trackStatMqVo));
            return true;
        } else {
            mongoTemplate.remove(query, MongoUtil.getCollectionName(MongoUtil.MongoCollectionEnum.USER_COLLECT, userId));
            TrackStatMqVo trackStatMqVo = new TrackStatMqVo();
            trackStatMqVo.setBusinessNo(UUID.randomUUID().toString().replaceAll("-", ""));
            trackStatMqVo.setTarckId(trackId);
            trackStatMqVo.setStatType(SystemConstant.COLLECT_NUM_TRACK);
            trackStatMqVo.setCount(-1);
            kafkaService.sendMessage(KafkaConstant.UPDATE_TRACK_STAT_QUEUE, JSON.toJSONString(trackStatMqVo));
            return false;
        }
    }

    @Override
    public IPage<UserCollectVo> getUserCollectByPage(Integer pageNum, Integer pageSize) {
        Long userId = AuthContextHolder.getUserId();
        Query query = Query.query(Criteria.where("userId").is(userId));
        PageRequest pageable = PageRequest.of(pageNum - 1, pageSize);
        query.with(pageable);
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        query.with(sort);
        List<UserCollect> userCollectList = mongoTemplate.find(query, UserCollect.class, MongoUtil.getCollectionName(MongoUtil.MongoCollectionEnum.USER_COLLECT, userId));
        long total = mongoTemplate.count(query, MongoUtil.getCollectionName(MongoUtil.MongoCollectionEnum.USER_COLLECT, userId));
        List<Long> trackIdList = userCollectList.stream().map(UserCollect::getTrackId).toList();
        List<UserCollectVo> userCollectVoList = null;
        if (!CollectionUtils.isEmpty(trackIdList)) {
            List<TrackTempVo> trackVoList = trackInfoService.getTrackVoList(trackIdList);
            Map<Long, TrackTempVo> trackTempVoMap = trackVoList.stream().collect(Collectors.toMap(TrackTempVo::getTrackId, trackVo -> trackVo));
            userCollectVoList = userCollectList.stream().map(userCollect -> {
                UserCollectVo userCollectVo = new UserCollectVo();
                Long trackId = userCollect.getTrackId();
                TrackTempVo trackTempVo = trackTempVoMap.get(trackId);
                BeanUtils.copyProperties(trackTempVo, userCollectVo);
                userCollectVo.setTrackId(trackId);
                userCollectVo.setCreateTime(userCollect.getCreateTime());
                return userCollectVo;
            }).toList();
        }
        return new Page<UserCollectVo>(pageNum, pageSize, total).setRecords(userCollectVoList);
    }

    @Override
    public IPage<UserListenProcessTempVo> getPlayHistoryTrackByPage(Integer pageNum, Integer pageSize) {
        Long userId = AuthContextHolder.getUserId();
        Query query = Query.query(Criteria.where("userId").is(userId));
        PageRequest pageable = PageRequest.of(pageNum - 1, pageSize);
        query.with(pageable);
        Sort sort = Sort.by(Sort.Direction.DESC, "updateTime");
        query.with(sort);
        List<UserListenProcess> userListenProcessList = mongoTemplate.find(query, UserListenProcess.class, MongoUtil.getCollectionName(MongoUtil.MongoCollectionEnum.USER_LISTEN_PROCESS, userId));
        long total = mongoTemplate.count(query, MongoUtil.getCollectionName(MongoUtil.MongoCollectionEnum.USER_LISTEN_PROCESS, userId));
        List<UserListenProcessTempVo> userListenProcessVoList = null;
        if (!CollectionUtils.isEmpty(userListenProcessList)) {
            List<Long> trackIdList = userListenProcessList.stream().map(UserListenProcess::getTrackId).toList();
            List<Long> albumIdList = userListenProcessList.stream().map(UserListenProcess::getAlbumId).toList();
            List<TrackTempVo> trackVoList = trackInfoService.getTrackVoList(trackIdList);
            List<AlbumTempVo> albumTempVoList = albumInfoService.getAlbumTempList(albumIdList);
            Map<Long, AlbumTempVo> albumTempVoMap = albumTempVoList.stream().collect(Collectors.toMap(AlbumTempVo::getAlbumId, albumTempVo -> albumTempVo));
            Map<Long, TrackTempVo> trackTempVoMap = trackVoList.stream().collect(Collectors.toMap(TrackTempVo::getTrackId, trackVo -> trackVo));
            userListenProcessVoList = userListenProcessList.stream().map(userListenProcess -> {
                UserListenProcessTempVo userListenProcessTempVo = new UserListenProcessTempVo();
                Long albumId = userListenProcess.getAlbumId();
                Long trackId = userListenProcess.getTrackId();
                AlbumTempVo albumTempVo = albumTempVoMap.get(albumId);
                TrackTempVo trackTempVo = trackTempVoMap.get(trackId);
                userListenProcessTempVo.setId(userListenProcess.getId());
                userListenProcessTempVo.setAlbumId(userListenProcess.getAlbumId());
                userListenProcessTempVo.setTrackId(userListenProcess.getTrackId());
                userListenProcessTempVo.setBreakSecond(userListenProcess.getBreakSecond());
                String coverUrl = StringUtil.isEmpty(trackTempVo.getCoverUrl()) ? albumTempVo.getCoverUrl() : trackTempVo.getCoverUrl();
                userListenProcessTempVo.setCoverUrl(coverUrl);
                userListenProcessTempVo.setAlbumTitle(albumTempVo.getAlbumTitle());
                userListenProcessTempVo.setTrackTitle(trackTempVo.getTrackTitle());
                userListenProcessTempVo.setMediaDuration(trackTempVo.getMediaDuration());
                String playRate = userListenProcess.getBreakSecond().divide(userListenProcessTempVo.getMediaDuration(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal(100)) + "%";
                userListenProcessTempVo.setPlayRate(playRate);
                return userListenProcessTempVo;
            }).toList();
        }
        return new Page<UserListenProcessTempVo>(pageNum, pageSize, total).setRecords(userListenProcessVoList);
    }


}
