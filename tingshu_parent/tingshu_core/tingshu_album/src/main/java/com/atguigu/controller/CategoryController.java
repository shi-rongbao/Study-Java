package com.atguigu.controller;

import com.atguigu.entity.*;
import com.atguigu.login.TingShuLogin;
import com.atguigu.mapper.BaseAttributeMapper;
import com.atguigu.result.RetVal;
import com.atguigu.service.AlbumAttributeValueService;
import com.atguigu.service.BaseCategory1Service;
import com.atguigu.service.BaseCategory3Service;
import com.atguigu.service.BaseCategoryViewService;
import com.atguigu.util.AuthContextHolder;
import com.atguigu.vo.CategoryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.simpleframework.xml.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 声音统计 前端控制器
 * </p>
 *
 * @author ShiRongbao
 * @since 2024-05-13
 */
@Tag(name = "分类管理")
@RestController
@RequestMapping(value = "/api/album/category")
public class CategoryController {

    @Resource
    private BaseCategoryViewService categoryViewService;

    @Resource
    private BaseAttributeMapper propertyKeyMapper;

    @Resource
    private BaseCategory3Service baseCategory3Service;

    @TingShuLogin()
    @Operation(summary = "获取全部分类信息")
    @GetMapping("/getAllCategoryList")
    public RetVal<List<CategoryVo>> getAllCategoryList() throws Exception {
        List<CategoryVo> categoryVoList = categoryViewService.getAllCategoryList(null);
        return RetVal.ok(categoryVoList);
    }

    @TingShuLogin()
    @Operation(summary = "据一级分类Id查询分类属性信息")
    @GetMapping("/getPropertyByCategory1Id/{category1Id}")
    public RetVal<List<BaseAttribute>> getPropertyByCategory1Id(@PathVariable Long category1Id) {
        List<BaseAttribute> categoryPropertyList = propertyKeyMapper.getPropertyByCategory1Id(category1Id);
        return RetVal.ok(categoryPropertyList);
    }

    @Operation(summary = "根据一级分类id获取三级分类列表")
    @GetMapping("/getCategory3ListByCategory1Id/{category1Id}")
    public RetVal<List<BaseCategory3>> getCategory3ListByCategory1Id(@PathVariable Long category1Id){
        return RetVal.ok(baseCategory3Service.getCategory3ListByCategory1Id(category1Id));
    }

    @Operation(summary = "根据一级分类id获取全部分类信息")
    @GetMapping("/getCategoryByCategory1Id/{category1Id}")
    public RetVal<CategoryVo> getCategoryByCategory1Id(@PathVariable Long category1Id){
        List<CategoryVo> allCategoryList = categoryViewService.getAllCategoryList(category1Id);
        if (!CollectionUtils.isEmpty(allCategoryList)) {
            return RetVal.ok(allCategoryList.get(0));
        }
        return RetVal.ok();
    }

    /**
     * 以下内容属于搜索模块
     **/
    @Resource
    private AlbumAttributeValueService albumAttributeValueService;

    @Resource
    private BaseCategory1Service baseCategory1Service;

    @Operation(summary = "根据三级分类id查询分类信息")
    @GetMapping("/getCategoryView/{category3Id}")
    public RetVal<BaseCategoryView> getCategoryView(@PathVariable Long category3Id) {
        BaseCategoryView categoryView = categoryViewService.getById(category3Id);
        return RetVal.ok(categoryView);
    }

    @Operation(summary = "查询所有的一级分类")
    @GetMapping("/getCategory1")
    public RetVal<List<BaseCategory1>> getCategory1(){
        LambdaQueryWrapper<BaseCategory1> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByAsc(BaseCategory1::getOrderNum);
        List<BaseCategory1> baseCategory1List = baseCategory1Service.list(wrapper);
        return RetVal.ok(baseCategory1List);
    }

}