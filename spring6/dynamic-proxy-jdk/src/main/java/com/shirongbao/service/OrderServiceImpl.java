package com.shirongbao.service;

public class OrderServiceImpl implements OrderService{
    @Override
    public String getName() {
        System.out.println("getName()方法执行了");
        return "张三";
    }

    @Override
    public void generate() {
        try {
            Thread.sleep(1234);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("订单正在生成~~~");
    }

    @Override
    public void modify() {
        try {
            Thread.sleep(321);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("订单正在修改~~~");
    }

    @Override
    public void detail() {
        try {
            Thread.sleep(432);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("请查看订单详情~~~");
    }
}
