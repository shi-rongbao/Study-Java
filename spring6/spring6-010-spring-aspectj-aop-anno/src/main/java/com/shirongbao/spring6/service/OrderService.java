package com.shirongbao.spring6.service;

import org.springframework.stereotype.Service;

@Service("orderService")
public class OrderService {
    public void generate(){
        System.out.println("正在生成订单信息");
    }
}
