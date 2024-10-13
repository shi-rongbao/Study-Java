package com.atguigu.service.impl;

import com.atguigu.UserFeignClient;
import com.atguigu.constant.SystemConstant;
import com.atguigu.entity.AlbumInfo;
import com.atguigu.entity.TrackInfo;
import com.atguigu.entity.TrackStat;
import com.atguigu.mapper.TrackInfoMapper;
import com.atguigu.result.RetVal;
import com.atguigu.service.AlbumInfoService;
import com.atguigu.service.TrackInfoService;
import com.atguigu.service.TrackStatService;
import com.atguigu.service.VodService;
import com.atguigu.util.AuthContextHolder;
import com.atguigu.vo.AlbumTrackListVo;
import com.atguigu.vo.TrackTempVo;
import com.atguigu.vo.UserInfoVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 声音信息 服务实现类
 * </p>
 *
 * @author ShiRongbao
 * @since 2024-05-13
 */
@Service
public class TrackInfoServiceImpl extends ServiceImpl<TrackInfoMapper, TrackInfo> implements TrackInfoService {

    @Resource
    private VodService vodService;

    @Resource
    private AlbumInfoService albumInfoService;

    @Resource
    private TrackStatService trackStatService;

    @Resource
    private UserFeignClient userFeignClient;

    @Override
    @Transactional
    public boolean saveTrackInfo(TrackInfo trackInfo) {
        // 设置声音的其他参数
        trackInfo.setUserId(AuthContextHolder.getUserId());
        trackInfo.setStatus(SystemConstant.TRACK_APPROVED);
        vodService.getTrackMediaInfo(trackInfo);
        // 查询专辑中声音编号最大的值
        // 创建过滤条件对象
        LambdaQueryWrapper<TrackInfo> wrapper = new LambdaQueryWrapper<>();
        // 查询当前专辑id的数据
        wrapper.eq(TrackInfo::getAlbumId, trackInfo.getAlbumId());
        // 根据orderNum降序排列
        wrapper.orderByAsc(TrackInfo::getOrderNum);
        // 只查询orderNum这个字段
        wrapper.select(TrackInfo::getOrderNum);
        // 只拿到第一个
        wrapper.last("limit 1");
        // 查询track
        TrackInfo maxOrderNumTrackInfo = getOne(wrapper);
        int orderNum = 1;
        if (maxOrderNumTrackInfo != null) {
            orderNum = maxOrderNumTrackInfo.getOrderNum() + 1;
        }
        // 设置新的orderNum
        trackInfo.setOrderNum(orderNum);
        // 保存声音
        boolean saveTrackInfo = save(trackInfo);
        // 更新专辑表中声音的数量
        AlbumInfo albumInfo = albumInfoService.getById(trackInfo.getAlbumId());
        // 设置声音数量加一
        albumInfo.setIncludeTrackCount(albumInfo.getIncludeTrackCount() + 1);
        // 保存专辑信息
        boolean saveAlbum = albumInfoService.updateById(albumInfo);
        // 初始化声音的统计数据
        List<TrackStat> trackStatList = buildTrackStatData(trackInfo.getId());
        // 保存声音统计信息
        boolean saveTrackStat = trackStatService.saveBatch(trackStatList);
        return saveTrackInfo && saveAlbum && saveTrackStat;
    }

    @Override
    public boolean updateTrackInfoById(TrackInfo trackInfo) {
        vodService.getTrackMediaInfo(trackInfo);
        return updateById(trackInfo);
    }

    @Override
    @Transactional
    public boolean deleteTrackInfo(Long trackId) {
        // 根据声音id查询声音信息
        TrackInfo trackInfo = getById(trackId);
        // 删除声音基本信息
        boolean removeTrackInfo = removeById(trackId);
        // 删除统计信息
        boolean trackStat = trackStatService.remove(new LambdaQueryWrapper<TrackStat>().eq(TrackStat::getTrackId, trackId));
        // 更新专辑中声音数量
        // 更新专辑表中声音的数量
        AlbumInfo albumInfo = albumInfoService.getById(trackInfo.getAlbumId());
        // 设置声音数量加一
        albumInfo.setIncludeTrackCount(albumInfo.getIncludeTrackCount() - 1);
        // 更新专辑信息
        albumInfoService.updateById(albumInfo);
        // 删除腾讯云
        vodService.removeTrack(trackInfo.getMediaFileId());
        return removeTrackInfo && trackStat;
    }

    @Override
    public Page<AlbumTrackListVo> getAlbumDetailTrackByPage(Page<AlbumTrackListVo> pageParam, Long albumId) {
        pageParam = baseMapper.getAlbumTrackAndStatInfo(pageParam, albumId);
        List<AlbumTrackListVo> albumTrackListVoList = pageParam.getRecords();
        AlbumInfo albumInfo = albumInfoService.getById(albumId);
        Long userId = AuthContextHolder.getUserId();
        // 如果用户没有登录，只能看到免费的内容
        if (userId == null) {
            // 判断专辑是否是免费的
            if (!SystemConstant.FREE_ALBUM.equals(albumInfo.getPayType())) {
                List<AlbumTrackListVo> albumTrackNeedPayList = albumTrackListVoList.stream().filter(f -> f.getOrderNum() > albumInfo.getTracksForFree()).toList();
                if (!CollectionUtils.isEmpty(albumTrackNeedPayList)) {
                    albumTrackNeedPayList.forEach(f -> f.setIsShowPaidMark(true));
                }
            }
        } else {
            boolean isNeedPay = false;
            // 如果是VIP免费
            if (SystemConstant.VIPFREE_ALBUM.equals(albumInfo.getPayType())) {
                UserInfoVo userInfo = userFeignClient.getUserInfo(userId).getData();
                // 用户是VIP
                if (userInfo.getIsVip() == 0) {
                    isNeedPay = true;
                }
                // vip过期了
                if (userInfo.getIsVip() == 1 && userInfo.getVipExpireTime().before(new Date())) {
                    isNeedPay = true;
                }
            } else if (SystemConstant.NEED_PAY_ALBUM.equals(albumInfo.getPayType())) {
                // 如果需要付费
                isNeedPay = true;
            }
            // 需要付费
            if (isNeedPay) {
                List<AlbumTrackListVo> albumTrackNeedPayList = albumTrackListVoList.stream().filter(f -> f.getOrderNum() > albumInfo.getTracksForFree()).toList();
                List<Long> needPayTrackList = albumTrackNeedPayList.stream().map(AlbumTrackListVo::getTrackId).toList();
                Map<Long, Boolean> showPaidMarkMap = userFeignClient.getUserShowPaidMarkOrNot(albumId, needPayTrackList).getData();
                albumTrackNeedPayList.forEach(albumTrackListVo -> albumTrackListVo.setIsShowPaidMark(showPaidMarkMap.get(albumTrackListVo.getTrackId())));
            }
        }
        return pageParam;
    }

    @Override
    public List<TrackTempVo> getTrackVoList(List<Long> trackIdList) {
        List<TrackInfo> trackInfoList = listByIds(trackIdList);
        return trackInfoList.stream().map(trackInfo -> {
            TrackTempVo trackTempVo = new TrackTempVo();
            BeanUtils.copyProperties(trackInfo, trackTempVo);
            trackTempVo.setTrackId(trackInfo.getId());
            return trackTempVo;
        }).toList();
    }

    @Override
    public List<Map<String, Object>> getTrackListToChoose(Long trackId) {
        TrackInfo trackInfo = getById(trackId);
        Long albumId = trackInfo.getAlbumId();
        AlbumInfo albumInfo = albumInfoService.getById(albumId);
        LambdaQueryWrapper<TrackInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TrackInfo::getAlbumId, albumId);
        wrapper.gt(TrackInfo::getOrderNum, trackInfo.getOrderNum());
        wrapper.select(TrackInfo::getId);
        List<TrackInfo> trackInfoList = list(wrapper);
        List<Long> trackIdList = trackInfoList.stream().map(TrackInfo::getId).toList();
        List<Long> paidTrackIdList = userFeignClient.getPaidTrackIdList(albumId).getData();
        List<Long> noPayTrackIdList = new ArrayList<>();
        if (CollectionUtils.isEmpty(paidTrackIdList)) {
            noPayTrackIdList = trackIdList;
        } else {
            noPayTrackIdList = trackIdList.stream().filter(tempTrackId -> !paidTrackIdList.contains(tempTrackId)).collect(Collectors.toList());
        }
        return getMaps(albumInfo, noPayTrackIdList);
    }

    @Override
    public List<TrackInfo> getTrackListPrepareToBuy(Long trackId, Integer trackCount) {
        TrackInfo trackInfo = getById(trackId);
        Long albumId = trackInfo.getAlbumId();
        String coverUrl = albumInfoService.getById(albumId).getCoverUrl();
        if (StringUtils.isEmpty(trackInfo.getCoverUrl())) {
            trackInfo.setCoverUrl(coverUrl);
        }
        List<TrackInfo> prepareToBuyTrackList = new ArrayList<>();
        List<Long> paidTrackIdList = userFeignClient.getPaidTrackIdList(albumId).getData();
        if (trackCount > 0) {
            LambdaQueryWrapper<TrackInfo> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(TrackInfo::getAlbumId, albumId);
            wrapper.gt(TrackInfo::getOrderNum, trackInfo.getOrderNum());
            wrapper.orderByAsc(TrackInfo::getOrderNum);
            if (!CollectionUtils.isEmpty(paidTrackIdList)) {
                wrapper.notIn(TrackInfo::getId, paidTrackIdList);
            }
            wrapper.last("limit " + trackCount);
            prepareToBuyTrackList = list(wrapper);
            prepareToBuyTrackList.forEach(track -> {
                if (StringUtils.isEmpty(track.getCoverUrl())) {
                    track.setCoverUrl(coverUrl);
                }
            });
        } else {
            prepareToBuyTrackList.add(trackInfo);
        }
        return prepareToBuyTrackList;
    }

    private static @NotNull List<Map<String, Object>> getMaps(AlbumInfo albumInfo, List<Long> noPayTrackIdList) {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> firstMap = new HashMap<>();
        firstMap.put("name", "本集");
        firstMap.put("price", albumInfo.getPrice());
        firstMap.put("trackCount", 0);
        list.add(firstMap);
        if (!noPayTrackIdList.isEmpty() && noPayTrackIdList.size() <= 10) {
            Map<String, Object> map = new HashMap<>();
            int count = noPayTrackIdList.size();
            BigDecimal price = albumInfo.getPrice().multiply(new BigDecimal(count));
            map.put("name", "后" + count + "集");
            map.put("price", price);
            map.put("trackCount", count);
            list.add(map);
        }
        if (noPayTrackIdList.size() > 10) {
            Map<String, Object> map = new HashMap<>();
            BigDecimal price = albumInfo.getPrice().multiply(new BigDecimal(10));
            map.put("name", "后10集");
            map.put("price", price);
            map.put("trackCount", 10);
            list.add(map);
        }
        if (noPayTrackIdList.size() > 10 && noPayTrackIdList.size() <= 20) {
            Map<String, Object> map = new HashMap<>();
            int count = noPayTrackIdList.size();
            BigDecimal price = albumInfo.getPrice().multiply(new BigDecimal(count));
            map.put("name", "后" + count + "集");
            map.put("price", price);
            map.put("trackCount", count);
            list.add(map);
        }
        if (noPayTrackIdList.size() > 20) {
            Map<String, Object> map = new HashMap<>();
            BigDecimal price = albumInfo.getPrice().multiply(new BigDecimal(20));
            map.put("name", "后20集");
            map.put("price", price);
            map.put("trackCount", 20);
            list.add(map);
        }
        if (noPayTrackIdList.size() > 30) {
            Map<String, Object> map = new HashMap<>();
            BigDecimal price = albumInfo.getPrice().multiply(new BigDecimal(30));
            map.put("name", "后30集");
            map.put("price", price);
            map.put("trackCount", 30);
            list.add(map);
        }
        if (noPayTrackIdList.size() > 40) {
            Map<String, Object> map = new HashMap<>();
            BigDecimal price = albumInfo.getPrice().multiply(new BigDecimal(40));
            map.put("name", "后40集");
            map.put("price", price);
            map.put("trackCount", 40);
            list.add(map);
        }
        if (noPayTrackIdList.size() > 50) {
            Map<String, Object> map = new HashMap<>();
            BigDecimal price = albumInfo.getPrice().multiply(new BigDecimal(50));
            map.put("name", "后10集");
            map.put("price", price);
            map.put("trackCount", 50);
            list.add(map);
        }
        return list;
    }

    // 构建轨道统计数据的方法
    private List<TrackStat> buildTrackStatData(Long trackId) {
        // 创建一个空的 TrackStat 列表
        List<TrackStat> trackStatList = new ArrayList<>();
        // 初始化 TrackStat 并添加到列表中，重复四次
        initTrackStat(trackId, trackStatList, SystemConstant.PLAY_NUM_TRACK);
        initTrackStat(trackId, trackStatList, SystemConstant.PLAY_NUM_TRACK);
        initTrackStat(trackId, trackStatList, SystemConstant.PLAY_NUM_TRACK);
        initTrackStat(trackId, trackStatList, SystemConstant.PLAY_NUM_TRACK);
        // 返回 TrackStat 列表
        return trackStatList;
    }

    // 初始化轨道统计数据的方法
    private void initTrackStat(Long trackId, List<TrackStat> trackStatList, String statType) {
        // 创建一个新的 TrackStat 对象
        TrackStat trackStat = new TrackStat();
        // 设置 TrackStat 对象的轨道 ID
        trackStat.setTrackId(trackId);
        // 设置 TrackStat 对象的统计类型
        trackStat.setStatType(statType);
        // 设置 TrackStat 对象的统计数值为 0
        trackStat.setStatNum(0);
        // 将 TrackStat 对象添加到 TrackStat 列表中
        trackStatList.add(trackStat);
    }

}
