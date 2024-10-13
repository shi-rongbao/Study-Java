package com.atguigu.mapper;

import com.atguigu.entity.AlbumStat;
import com.atguigu.vo.AlbumStatVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import feign.Param;

/**
 * <p>
 * 专辑统计 Mapper 接口
 * </p>
 *
 * @author ShiRongbao
 * @since 2024-05-13
 */
public interface AlbumStatMapper extends BaseMapper<AlbumStat> {

    /**
     * 根据albumId 查询专辑统计信息，返回AlbumStatVo对象
     * @param albumId albumId
     * @return AlbumStatVo对象
     */
    AlbumStatVo getAlbumStatInfo(@Param("albumId") Long albumId);
}
