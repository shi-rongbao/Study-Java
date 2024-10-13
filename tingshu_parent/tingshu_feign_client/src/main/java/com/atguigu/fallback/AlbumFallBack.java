package com.atguigu.fallback;

import com.atguigu.AlbumFeignClient;
import com.atguigu.entity.AlbumAttributeValue;
import com.atguigu.entity.AlbumInfo;
import com.atguigu.entity.TrackInfo;
import com.atguigu.result.RetVal;
import com.atguigu.vo.AlbumStatVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShiRongbao
 * @create 2024/7/17 0:09
 * @description:
 */
@Component
@Slf4j
public class AlbumFallBack implements AlbumFeignClient {
    @Override
    public RetVal<AlbumInfo> getAlbumInfoById(Long albumId) {
        log.warn("服务降级，根据albumId查询专辑信息");
        return RetVal.fail(new AlbumInfo()).message("对不起，系统正忙！");
    }

    @Override
    public RetVal<List<AlbumAttributeValue>> getAlbumPropertyValue(Long albumId) {
        List<AlbumAttributeValue> list = new ArrayList<>();
        log.warn("服务降级，根据albumId查询专辑属性值");
        return RetVal.fail(list).message("对不起，系统正忙！");
    }

    @Override
    public AlbumStatVo getAlbumStatInfo(Long albumId) {
        log.warn("服务降级，获取专辑统计信息");
        return null;
    }

    @Override
    public RetVal<List<TrackInfo>> getTrackListPrepareToBuy(Long trackId, Integer trackCount) {
        List<TrackInfo> list = new ArrayList<>();
        return RetVal.fail(list).message("对不起，系统正忙！");
    }

    @Override
    public RetVal<TrackInfo> getTrackInfoById(Long trackId) {
        return RetVal.fail(new TrackInfo()).message("对不起，系统正忙！");
    }
}
