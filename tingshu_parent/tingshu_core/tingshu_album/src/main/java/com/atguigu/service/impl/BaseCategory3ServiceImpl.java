package com.atguigu.service.impl;

import com.atguigu.constant.SystemConstant;
import com.atguigu.entity.BaseCategory2;
import com.atguigu.entity.BaseCategory3;
import com.atguigu.mapper.BaseCategory3Mapper;
import com.atguigu.service.BaseCategory2Service;
import com.atguigu.service.BaseCategory3Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 三级分类表 服务实现类
 * </p>
 *
 * @author ShiRongbao
 * @since 2024-05-13
 */
@Service
public class BaseCategory3ServiceImpl extends ServiceImpl<BaseCategory3Mapper, BaseCategory3> implements BaseCategory3Service {

    @Resource
    private BaseCategory2Service baseCategory2Service;

    @Resource
    private BaseCategory3Service baseCategory3Service;

    @Override
    public List<BaseCategory3> getCategory3ListByCategory1Id(Long category1Id) {
        // 先根据category1Id查询base_category2表中的category2Id
        LambdaQueryWrapper<BaseCategory2> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BaseCategory2::getCategory1Id, category1Id);
        // 排序
        wrapper.orderByDesc(BaseCategory2::getOrderNum);
        wrapper.select(BaseCategory2::getId);
        List<BaseCategory2> baseCategory2List = baseCategory2Service.list(wrapper);
        // 将查到的category2Id收集起来
        List<Long> baseCategory2IdList = baseCategory2List.stream().map(BaseCategory2::getId).toList();
        // 再根据category2Id查询base_category3表中的数据
        LambdaQueryWrapper<BaseCategory3> wrapper3 = new LambdaQueryWrapper<>();
        wrapper3.in(BaseCategory3::getCategory2Id, baseCategory2IdList);
        wrapper3.eq(BaseCategory3::getIsTop, 1).last("limit 7");
        return baseCategory3Service.list(wrapper3);
    }
}
