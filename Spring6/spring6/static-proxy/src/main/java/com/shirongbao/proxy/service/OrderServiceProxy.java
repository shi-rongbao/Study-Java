package com.shirongbao.proxy.service;

public class OrderServiceProxy implements OrderService {
    private OrderService target;

    public OrderServiceProxy(OrderService target) {
        this.target = target;
    }

    @Override
    public void generate() {
        Long begin = System.currentTimeMillis();
        target.generate();
        Long end = System.currentTimeMillis();
        System.out.println("总共耗时" + (end - begin) + "毫秒");
    }

    @Override
    public void modify() {
        Long begin = System.currentTimeMillis();
        target.modify();
        Long end = System.currentTimeMillis();
        System.out.println("总共耗时" + (end - begin) + "毫秒");
    }

    @Override
    public void detail() {
        Long begin = System.currentTimeMillis();
        target.detail();
        Long end = System.currentTimeMillis();
        System.out.println("总共耗时" + (end - begin) + "毫秒");
    }
}
