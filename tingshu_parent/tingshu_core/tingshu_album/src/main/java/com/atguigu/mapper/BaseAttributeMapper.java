package com.atguigu.mapper;

import com.atguigu.entity.BaseAttribute;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 属性表 Mapper 接口
 * </p>
 *
 * @author ShiRongbao
 * @since 2024-05-13
 */
public interface BaseAttributeMapper extends BaseMapper<BaseAttribute> {

    /**
     * 根据一级分类id查询分类属性信息
     * @param category1Id 一级分类id
     * @return 返回集合
     */
    List<BaseAttribute> getPropertyByCategory1Id(@Param("category1Id") Long category1Id);
}
