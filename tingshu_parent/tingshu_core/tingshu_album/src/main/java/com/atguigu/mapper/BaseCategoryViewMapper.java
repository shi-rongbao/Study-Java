package com.atguigu.mapper;

import com.atguigu.entity.BaseCategoryView;
import com.atguigu.vo.CategoryVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jakarta.websocket.server.PathParam;

import java.util.List;

/**
 * <p>
 * VIEW Mapper 接口
 * </p>
 *
 * @author ShiRongbao
 * @since 2024-05-13
 */
public interface BaseCategoryViewMapper extends BaseMapper<BaseCategoryView> {

    /**
     * 查询所有的分类信息
     * @return 返回分类信息
     */
    List<CategoryVo> getAllCategoryList(@PathParam("category1Id") Long category1Id);
}
