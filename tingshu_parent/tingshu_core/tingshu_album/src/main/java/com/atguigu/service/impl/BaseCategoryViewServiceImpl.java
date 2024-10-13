package com.atguigu.service.impl;

import com.atguigu.cache.TingShuCache;
import com.atguigu.entity.BaseCategory1;
import com.atguigu.entity.BaseCategoryView;
import com.atguigu.mapper.BaseCategory1Mapper;
import com.atguigu.mapper.BaseCategoryViewMapper;
import com.atguigu.service.BaseCategory1Service;
import com.atguigu.service.BaseCategoryViewService;
import com.atguigu.vo.CategoryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * VIEW 服务实现类
 * </p>
 *
 * @author ShiRongbao
 * @since 2024-05-13
 */
@Service
public class BaseCategoryViewServiceImpl extends ServiceImpl<BaseCategoryViewMapper, BaseCategoryView> implements BaseCategoryViewService {

    // @Override
    public List<CategoryVo> getAllCategoryList1() {
        // 1.查询所有的分类信息
        List<BaseCategoryView> allCategoryView = list();
        // 2.找到所有的一级分类
        Map<Long, List<BaseCategoryView>> category1Map = allCategoryView.stream().collect(Collectors.groupingBy(BaseCategoryView::getCategory1Id));
        // 创建装有分好类的集合
        // List<CategoryVo> categoryVoList = new ArrayList<>();
        // 遍历一级分类
        // for (Map.Entry<Long, List<BaseCategoryView>> category1Entry : category1Map.entrySet()) {
        return category1Map.entrySet().stream().map(category1Entry -> {
            // 拿到一级分类的id
            Long category1Id = category1Entry.getKey();
            // 拿到一级分类的集合
            List<BaseCategoryView> category1List = category1Entry.getValue();
            // 当集合不为空时
            // 创建一个CategoryVo对象
            CategoryVo category1Vo = null;
            if (category1List != null && !category1List.isEmpty()) {
                // 设置Vo的值
                category1Vo = new CategoryVo();
                category1Vo.setCategoryId(category1Id);
                category1Vo.setCategoryName(category1List.get(0).getCategory1Name());
                // 3.找到所有的二级分类   通过二级id分类
                Map<Long, List<BaseCategoryView>> category2Map = category1List.stream().collect(Collectors.groupingBy(BaseCategoryView::getCategory2Id));
                // 二级分类要返回给一级分类，通过stream的map方法遍历并返回
                List<CategoryVo> category1Children = category2Map.entrySet().stream().map(category2Entry -> {
                    // 拿到二级id
                    Long category2Id = category2Entry.getKey();
                    // 拿到二级集合
                    List<BaseCategoryView> category2List = category2Entry.getValue();
                    // new 出Vo对象，这里不需要做非空判断
                    CategoryVo category2Vo = new CategoryVo();
                    category2Vo.setCategoryId(category2Id);
                    category2Vo.setCategoryName(category2List.get(0).getCategory2Name());
                    // 4.找到所有的三级分类
                    Map<Long, List<BaseCategoryView>> category3Map = category2List.stream().collect(Collectors.groupingBy(BaseCategoryView::getCategory3Id));
                    // 通过stream的map方法遍历并返回
                    List<CategoryVo> category2Children = category3Map.entrySet().stream().map(category3Entry -> {
                        // 拿到三级id
                        Long category3Id = category3Entry.getKey();
                        // 拿到三级集合
                        List<BaseCategoryView> category3List = category3Entry.getValue();
                        // 创建新的Vo对象
                        CategoryVo category3Vo = new CategoryVo();
                        // 设置3id的值
                        category3Vo.setCategoryId(category3Id);
                        // 设置3name的值
                        category3Vo.setCategoryName(category3List.get(0).getCategory3Name());
                        // 3vo没有子分类了
                        category3Vo.setCategoryChild(null);
                        // 返回3vo
                        return category3Vo;
                    }).collect(Collectors.toList());
                    // 设置2vo的子分类
                    category2Vo.setCategoryChild(category2Children);
                    // 返回2vo
                    return category2Vo;
                }).collect(Collectors.toList());
                // 将返回来的的子分类设置为1Vo的值
                category1Vo.setCategoryChild(category1Children);
            }
            // 这里如果是null，返回一个null并收集并不会NullPointer,而是[]
            return category1Vo;
        }).collect(Collectors.toList());
    }


    @Override
    public List<CategoryVo> getAllCategoryList(Long category1Id) {
        return baseMapper.getAllCategoryList(category1Id);
    }
}
