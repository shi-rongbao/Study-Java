package com.atguigu.mapper;

import com.atguigu.entity.TrackInfo;
import com.atguigu.query.TrackInfoQuery;
import com.atguigu.vo.AlbumTrackListVo;
import com.atguigu.vo.TrackStatVo;
import com.atguigu.vo.TrackTempVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 声音信息 Mapper 接口
 * </p>
 *
 * @author ShiRongbao
 * @since 2024-05-13
 */
public interface TrackInfoMapper extends BaseMapper<TrackInfo> {

    /**
     * 根据分页信息与查询条件分页
     * @param pageParam 分页信息
     * @param trackInfoQuery 查询条件
     * @return 返回分页信息
     */
    IPage<TrackTempVo> findUserTrackPage(@Param("pageParam") IPage<TrackTempVo> pageParam, @Param("trackInfoQuery") TrackInfoQuery trackInfoQuery);

    /**
     * 根据分页信息和专辑id分页查询专辑声音详情
     * @param pageParam 分页信息
     * @param albumId 专辑id
     * @return 返回专辑声音详情的分页信息
     */
    Page<AlbumTrackListVo> getAlbumTrackAndStatInfo(@Param("pageParam") Page<AlbumTrackListVo> pageParam, @Param("albumId") Long albumId);

    TrackStatVo getTrackStatistics(@Param("trackId") Long trackId);
}
