package com.atguigu.service;

import com.atguigu.entity.TrackInfo;
import com.atguigu.vo.AlbumTrackListVo;
import com.atguigu.vo.TrackTempVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 声音信息 服务类
 * </p>
 *
 * @author ShiRongbao
 * @since 2024-05-13
 */
public interface TrackInfoService extends IService<TrackInfo> {

    /**
     * 保存声音信息
     * @param trackInfo 要保存的声音信息
     * @return 保存成功返回true，否则返回false
     */
    boolean saveTrackInfo(TrackInfo trackInfo);

    /**
     * 根据传入的trackInfo信息修改trackInfo
     * @param trackInfo 传入的声音信息
     * @return 修改成功返回true，否则返回false
     */
    boolean updateTrackInfoById(TrackInfo trackInfo);

    /**
     * 根据trackId删除声音信息
     * @param trackId 声音id
     * @return 删除成功返回true，否则返回false
     */
    boolean deleteTrackInfo(Long trackId);

    /**
     * 根据分页数据和albumId查询专辑声音详情
     * @param pageParam 分页数据
     * @param albumId 专辑id
     * @return 专辑声音详情的分页数据
     */
    Page<AlbumTrackListVo> getAlbumDetailTrackByPage(Page<AlbumTrackListVo> pageParam, Long albumId);

    /**
     * 根据trackId集合查询
     *
     * @param trackIdList trackIdList
     * @return 返回trackTempVo集合
     */
    List<TrackTempVo> getTrackVoList(List<Long> trackIdList);

    /**
     * 根据trackId查询用户购买的声音列表
     *
     * @param trackId trackId
     * @return 返回购买信息
     */
    List<Map<String, Object>> getTrackListToChoose(Long trackId);

    /**
     * 根据trackId，trackCount获取要购买的声音的列表
     * @param trackId trackId
     * @param trackCount trackCount
     * @return 返回要购买的声音的列表
     */
    List<TrackInfo> getTrackListPrepareToBuy(Long trackId, Integer trackCount);
}
