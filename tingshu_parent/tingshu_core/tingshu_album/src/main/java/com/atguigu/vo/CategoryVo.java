package com.atguigu.vo;

import lombok.Data;

import java.util.List;

/**
 * @author ShiRongbao
 * @create 2024/5/14 10:59
 * @description:
 */
@Data
public class CategoryVo {
    private Long categoryId;
    private String categoryName;
    private List<CategoryVo> categoryChild;
}

