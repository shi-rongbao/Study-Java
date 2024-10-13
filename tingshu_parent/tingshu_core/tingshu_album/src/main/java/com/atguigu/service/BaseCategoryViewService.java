package com.atguigu.service;

import com.atguigu.entity.BaseCategoryView;
import com.atguigu.vo.CategoryVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * VIEW 服务类
 * </p>
 *
 * @author ShiRongbao
 * @since 2024-05-13
 */
public interface BaseCategoryViewService extends IService<BaseCategoryView> {

    /**
     * 查询所有分类接口
     * @return 返回查询到的结果，结果有多个，封装到集合中
     */
    List<CategoryVo> getAllCategoryList(Long category1Id);

}
