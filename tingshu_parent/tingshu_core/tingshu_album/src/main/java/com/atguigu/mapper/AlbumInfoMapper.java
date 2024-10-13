package com.atguigu.mapper;

import com.atguigu.entity.AlbumInfo;
import com.atguigu.query.AlbumInfoQuery;
import com.atguigu.vo.AlbumTempVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 专辑信息 Mapper 接口
 * </p>
 *
 * @author ShiRongbao
 * @since 2024-05-13
 */
public interface AlbumInfoMapper extends BaseMapper<AlbumInfo> {

    /**
     * 专辑分页查询
     * @param pageParam 分页信息
     * @param albumInfoQuery 查询过滤条件
     * @return 返回查询到的分页信息
     */
    IPage<AlbumTempVo> getUserAlbumByPage(@Param("pageParam") IPage<AlbumTempVo> pageParam, @Param("albumInfoQuery")AlbumInfoQuery albumInfoQuery);
}
