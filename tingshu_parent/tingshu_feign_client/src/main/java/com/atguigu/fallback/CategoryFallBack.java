package com.atguigu.fallback;

import com.atguigu.CategoryFeignClient;
import com.atguigu.entity.BaseCategory1;
import com.atguigu.entity.BaseCategory3;
import com.atguigu.entity.BaseCategoryView;
import com.atguigu.result.RetVal;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShiRongbao
 * @create 2024/7/17 12:38
 * @description:
 */
@Component
public class CategoryFallBack implements CategoryFeignClient {

    @Override
    public RetVal<BaseCategoryView> getCategoryView(Long category3Id) {
        return RetVal.fail(new BaseCategoryView()).message("对不起，系统正忙！");
    }

    @Override
    public RetVal<List<BaseCategory3>> getCategory3ListByCategory1Id(Long category1Id) {
        List<BaseCategory3> list = new ArrayList<>();
        return RetVal.fail(list).message("对不起，系统正忙！");
    }

    @Override
    public RetVal<List<BaseCategory1>> getCategory1() {
        List<BaseCategory1> list = new ArrayList<>();
        return RetVal.fail(list).message("对不起，系统正忙！");
    }
}
