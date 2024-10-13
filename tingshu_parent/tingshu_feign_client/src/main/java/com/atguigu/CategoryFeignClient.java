package com.atguigu;

import com.atguigu.entity.BaseCategory1;
import com.atguigu.entity.BaseCategory3;
import com.atguigu.entity.BaseCategoryView;
import com.atguigu.fallback.CategoryFallBack;
import com.atguigu.result.RetVal;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author ShiRongbao
 * @create 2024/7/17 12:37
 * @description:
 */
@FeignClient(value = "tingshu-album", fallback = CategoryFallBack.class)
public interface CategoryFeignClient {

    @GetMapping("/api/album/category/getCategoryView/{category3Id}")
    RetVal<BaseCategoryView> getCategoryView(@PathVariable Long category3Id);

    @GetMapping("/api/album/category/getCategory3ListByCategory1Id/{category1Id}")
    RetVal<List<BaseCategory3>> getCategory3ListByCategory1Id(@PathVariable Long category1Id);

    @GetMapping("/api/album/category/getCategory1")
    RetVal<List<BaseCategory1>> getCategory1();
}
