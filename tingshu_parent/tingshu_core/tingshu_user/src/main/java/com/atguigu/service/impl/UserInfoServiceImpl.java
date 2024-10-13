package com.atguigu.service.impl;

import com.atguigu.AlbumFeignClient;
import com.atguigu.constant.SystemConstant;
import com.atguigu.entity.*;
import com.atguigu.mapper.UserInfoMapper;
import com.atguigu.service.*;
import com.atguigu.util.AuthContextHolder;
import com.atguigu.vo.UserPaidRecordVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author ShiRongbao
 * @since 2024-05-13
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Resource
    private UserPaidAlbumService userPaidAlbumService;

    @Resource
    private UserPaidTrackService userPaidTrackService;

    @Resource
    private AlbumFeignClient albumFeignClient;

    @Resource
    private UserVipInfoService userVipInfoService;

    @Resource
    private VipServiceConfigService vipServiceConfigService;

    @Override
    public Map<Long, Boolean> getUserShowPaidMarkOrNot(Long albumId, List<Long> needPayTrackIdList) {
        Map<Long, Boolean> showPaidMarkMap = new HashMap<>();
        Long userId = AuthContextHolder.getUserId();
        // 查询用户购买的专辑（整个专辑中所有声音全部解锁）
        LambdaQueryWrapper<UserPaidAlbum> wrapperAlbum = new LambdaQueryWrapper<>();
        wrapperAlbum.eq(UserPaidAlbum::getUserId, userId);
        wrapperAlbum.eq(UserPaidAlbum::getAlbumId, albumId);
        UserPaidAlbum userPaidAlbum = userPaidAlbumService.getOne(wrapperAlbum);
        if (userPaidAlbum != null) {
            needPayTrackIdList.forEach(trackId -> showPaidMarkMap.put(trackId, false));
        } else {
            // 查询用户购买的声音
            LambdaQueryWrapper<UserPaidTrack> wrapperTrack = new LambdaQueryWrapper<>();
            wrapperTrack.eq(UserPaidTrack::getUserId, userId);
            wrapperTrack.in(UserPaidTrack::getTrackId, needPayTrackIdList);
            List<UserPaidTrack> userPaidTrackList = userPaidTrackService.list(wrapperTrack);
            List<Long> userPaidTrackIdList = userPaidTrackList.stream().map(UserPaidTrack::getTrackId).toList();
            needPayTrackIdList.forEach(trackId -> {
                if (userPaidTrackIdList.contains(trackId)) {
                    showPaidMarkMap.put(trackId, false);
                } else {
                    showPaidMarkMap.put(trackId, true);
                }
            });
        }
        return showPaidMarkMap;
    }

    @Override
    @Transactional
    public void updateUserPaidInfo(UserPaidRecordVo userPaidRecordVo) {
        String orderNo = userPaidRecordVo.getOrderNo();
        // 如果购买专辑
        Long userId = userPaidRecordVo.getUserId();
        Long itemId = userPaidRecordVo.getItemIdList().get(0);
        if (SystemConstant.BUY_ALBUM.equals(userPaidRecordVo.getItemType())) {
            // 防止重复消费
            // 一个新的订单号，第一次被消费时就存入数据库，如果再次消费，那么根据这个订单号一定能找到大于0条数据，因此重复消费，直接return
            long count = userPaidAlbumService.count(new LambdaQueryWrapper<UserPaidAlbum>().eq(UserPaidAlbum::getOrderNo, orderNo));
            if (count > 0) return;
            UserPaidAlbum userPaidAlbum = new UserPaidAlbum();
            userPaidAlbum.setUserId(userId);
            userPaidAlbum.setAlbumId(itemId);
            userPaidAlbum.setOrderNo(orderNo);
            // 保存用户支付专辑数据库
            userPaidAlbumService.save(userPaidAlbum);
        } else if (SystemConstant.BUY_TRACK.equals(userPaidRecordVo.getItemType())) {
            // 如果购买声音
            // 防止重复消费，与上面一致
            long count = userPaidTrackService.count(new LambdaQueryWrapper<UserPaidTrack>().eq(UserPaidTrack::getOrderNo, orderNo));
            if (count > 0) return;
            // 远程调用获取声音信息
            TrackInfo trackInfo = albumFeignClient.getTrackInfoById(itemId).getData();
            List<UserPaidTrack> userPaidTrackList = userPaidRecordVo.getItemIdList().stream().map(trackId -> {
                UserPaidTrack userPaidTrack = new UserPaidTrack();
                userPaidTrack.setOrderNo(orderNo);
                userPaidTrack.setUserId(userId);
                userPaidTrack.setAlbumId(trackInfo.getAlbumId());
                userPaidTrack.setTrackId(trackId);
                return userPaidTrack;
            }).toList();
            // 批量保存用户支付声音数据库，这里所有声音的orderNo是相同的
            userPaidTrackService.saveBatch(userPaidTrackList);
        } else if (SystemConstant.BUY_VIP.equals(userPaidRecordVo.getItemType())) {
            // 如果购买VIP
            VipServiceConfig vipServiceConfig = vipServiceConfigService.getById(itemId);
            UserVipInfo userVipInfo = new UserVipInfo();
            userVipInfo.setUserId(userId);
            userVipInfo.setOrderNo(orderNo);
            // 拿到用户信息
            UserInfo userInfo = getById(userId);
            Date startTime = new Date();
            // 判断当前用户是否为vip，如果是vip且没有过期，vip时间累加
            if (userInfo.getIsVip() == 1 && userInfo.getVipExpireTime().after(new Date())) {
                startTime = userInfo.getVipExpireTime();
            }
            Date newExpireTime = new DateTime(startTime).plusMonths(vipServiceConfig.getServiceMonth()).toDate();
            userVipInfo.setStartTime(startTime);
            userVipInfo.setExpireTime(newExpireTime);
            // 购买VIP只需要续期就可以了
            userVipInfoService.save(userVipInfo);
            // 更新用户VIP信息
            userInfo.setIsVip(1);
            userInfo.setVipExpireTime(newExpireTime);
            // 更新用户信息
            updateById(userInfo);
        }
    }
}
