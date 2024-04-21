package com.study.test4;

import java.util.Random;

public class MyThread extends Thread {

    //定义变量,用于存储红包信息
    static double money = 100;
    static int count = 3;

    //最小终将金额
    static final double MIN = 0.01;


    @Override
    public void run() {
        //因为抢红包每个人只能抢一次,就不用循环了
        synchronized (MyThread.class) {
            //当count 为0,代表红包已经抢完了,
            if (count == 0) {
                //这时如果有线程进入,则输出没有抢到
                System.out.println(getName() + "没有抢到红包");
            } else {
                double price = 0.0;
                if (count == 1) {
                    //这时表示最后一个红包,将所有的钱获取
                    price = money;
                    System.out.println(getName() + "抢到" + price + "元");
                } else {
                    Random r = new Random();
                    //第一个红包最多抽到99.98元
                    double max = money - (count - 1) * MIN;
                    price = r.nextDouble(max);//这个随机有可能随机到0
                    if (price < MIN) {
                        price = MIN;
                    }
                }
                money = money - price;
                count--;
                System.out.println(getName() + "抢到" + price + "元");
            }
        }
    }
}
