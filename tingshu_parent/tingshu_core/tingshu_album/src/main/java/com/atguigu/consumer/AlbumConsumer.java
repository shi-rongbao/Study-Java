package com.atguigu.consumer;

import com.alibaba.csp.sentinel.util.StringUtil;
import com.alibaba.fastjson.JSON;
import com.atguigu.constant.KafkaConstant;
import com.atguigu.constant.SystemConstant;
import com.atguigu.entity.AlbumStat;
import com.atguigu.entity.TrackInfo;
import com.atguigu.entity.TrackStat;
import com.atguigu.service.AlbumStatService;
import com.atguigu.service.TrackInfoService;
import com.atguigu.service.TrackStatService;
import com.atguigu.vo.AlbumStatMqVo;
import com.atguigu.vo.TrackStatMqVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tencentcloudapi.ame.v20190916.models.Album;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
 * @author ShiRongbao
 * @create 2024/7/25 14:09
 * @description:
 */
@Component
public class AlbumConsumer {

    @Resource
    private TrackStatService trackStatService;

    @Resource
    private AlbumStatService albumStatService;

    @Resource
    private RedisTemplate<String, Long> redisTemplate;

    @Resource
    private TrackInfoService trackInfoService;

    @KafkaListener(topics = KafkaConstant.UPDATE_TRACK_STAT_QUEUE)
    @Transactional
    public void updateStat(String dataJson) {
        TrackStatMqVo trackStatMqVo = JSON.parseObject(dataJson, TrackStatMqVo.class);
        // 防止重复消费
        String key = trackStatMqVo.getBusinessNo();
        Boolean isExist = redisTemplate.opsForValue().setIfAbsent(key, 1L, 20, TimeUnit.SECONDS);
        if (Boolean.TRUE.equals(isExist)) {
            String statType = trackStatMqVo.getStatType();
            LambdaQueryWrapper<TrackStat> trackStatWrapper = new LambdaQueryWrapper<>();
            trackStatWrapper.eq(TrackStat::getTrackId, trackStatMqVo.getTarckId());
            trackStatWrapper.eq(TrackStat::getStatType, statType);
            TrackStat trackStat = trackStatService.getOne(trackStatWrapper);
            trackStat.setStatNum(trackStat.getStatNum() + trackStatMqVo.getCount());
            trackStatService.updateById(trackStat);
            // 更新专辑播放量
            if (statType.equals(SystemConstant.PLAY_NUM_TRACK)) {
                LambdaQueryWrapper<AlbumStat> albumStatWrapper = new LambdaQueryWrapper<>();
                albumStatWrapper.eq(AlbumStat::getAlbumId, trackStatMqVo.getAlbumId());
                albumStatWrapper.eq(AlbumStat::getStatType, SystemConstant.PLAY_NUM_ALBUM);
                AlbumStat albumStat = albumStatService.getOne(albumStatWrapper);
                albumStat.setStatNum(albumStat.getStatNum() + trackStatMqVo.getCount());
                albumStatService.updateById(albumStat);
                // 更新ES里面播放量信息，如果后面想做，做一个定时任务，定时往ES里面更新，每次更新播放量都更新ES里的数据，消耗太大
            }
        }
    }

    @KafkaListener(topics = KafkaConstant.UPDATE_ALBUM_BUY_NUM_QUEUE)
    public void updateAlbumStat(String dataJson) {
        if (StringUtil.isNotBlank(dataJson)) {
            AlbumStatMqVo albumStatMqVo = JSON.parseObject(dataJson, AlbumStatMqVo.class);
            String businessNo = albumStatMqVo.getBusinessNo();
            Long albumId = albumStatMqVo.getAlbumId();
            String statType = albumStatMqVo.getStatType();
            // 防止重复消费
            Boolean isExist = redisTemplate.opsForValue().setIfAbsent(businessNo, 1L, 20, TimeUnit.SECONDS);
            if (Boolean.TRUE.equals(isExist)) {
                // 更新专辑的购买量
                LambdaQueryWrapper<AlbumStat> wrapper = new LambdaQueryWrapper<>();
                wrapper.eq(AlbumStat::getAlbumId, albumId);
                wrapper.eq(AlbumStat::getStatType, statType);
                AlbumStat albumStat = albumStatService.getOne(wrapper);
                albumStat.setStatNum(albumStat.getStatNum() + 1);
                // 更新专辑的购买量
                albumStatService.updateById(albumStat);
                // 再删掉防止重复消费的锁
                redisTemplate.delete(businessNo);
            }
        }
    }


}
