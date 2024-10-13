package com.atguigu.service;

import com.atguigu.entity.BaseCategory3;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 三级分类表 服务类
 * </p>
 *
 * @author ShiRongbao
 * @since 2024-05-13
 */
public interface BaseCategory3Service extends IService<BaseCategory3> {

    /**
     * 根据category1Id 查询三级分类列表
     * @param category1Id category1Id
     * @return 返回一个集合，里面装着三级分类信息
     */
    List<BaseCategory3> getCategory3ListByCategory1Id(Long category1Id);
}
