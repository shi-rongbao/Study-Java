package com.shirongbao.cloud.service;

import com.shirongbao.cloud.entities.Order;

/**
 * @author ShiRongbao
 * @create 2024/3/24 22:18
 * @description:
 */
public interface OrderService {

    /**
     * 创建订单
     */
    void create(Order order);

}
