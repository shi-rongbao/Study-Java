package com.shirongbao.cloud.service;

/**
 * @author ShiRongbao
 * @create 2024/3/24 22:47
 * @description:
 */
public interface StorageService{
    /**
     * 扣减库存
     */
    void decrease(Long productId, Integer count);
}
