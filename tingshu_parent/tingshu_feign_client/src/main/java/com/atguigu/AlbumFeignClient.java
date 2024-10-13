package com.atguigu;

import com.atguigu.entity.AlbumAttributeValue;
import com.atguigu.entity.AlbumInfo;
import com.atguigu.entity.TrackInfo;
import com.atguigu.fallback.AlbumFallBack;
import com.atguigu.result.RetVal;
import com.atguigu.vo.AlbumStatVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author ShiRongbao
 * @create 2024/7/17 0:00
 * @description:
 */
@FeignClient(value = "tingshu-album", fallback = AlbumFallBack.class)
public interface AlbumFeignClient {

    @GetMapping("/api/album/albumInfo/getAlbumInfoById/{albumId}")
    RetVal<AlbumInfo> getAlbumInfoById(@PathVariable Long albumId);

    @GetMapping("/api/album/albumInfo/getAlbumPropertyValue/{albumId}")
    RetVal<List<AlbumAttributeValue>> getAlbumPropertyValue(@PathVariable Long albumId);

    @GetMapping("/api/album/albumInfo/getAlbumStatInfo/{albumId}")
    AlbumStatVo getAlbumStatInfo(@PathVariable Long albumId);

    @GetMapping("/api/album/trackInfo/getTrackListPrepareToBuy/{trackId}/{trackCount}")
    RetVal<List<TrackInfo>> getTrackListPrepareToBuy(@PathVariable Long trackId, @PathVariable Integer trackCount);

    @GetMapping("/api/album/trackInfo/getTrackInfoById/{trackId}")
    RetVal<TrackInfo> getTrackInfoById(@PathVariable Long trackId);

}
