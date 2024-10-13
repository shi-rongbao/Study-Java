package com.shirongbao.proxy.clint;

import com.shirongbao.proxy.service.OrderService;
import com.shirongbao.proxy.service.OrderServiceImpl;
import com.shirongbao.proxy.service.OrderServiceProxy;

public class Test {
    public static void main(String[] args) {
//        OrderService service = new OrderServiceImpl();
//        service.detail();
//        service.generate();
//        service.modify();

        // 目标对象
        OrderService target = new OrderServiceImpl();
        // 代理对象
        OrderService proxy = new OrderServiceProxy(target);
        proxy.detail();
        proxy.generate();
        proxy.modify();


    }
}
