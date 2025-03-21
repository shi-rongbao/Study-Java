package com.study.test6;

import java.util.ArrayList;
import java.util.Collections;

public class ThreadDemo {
    public static void main(String[] args) {
        /*
            在上一题基础上继续完成如下需求:
                每次抽的过程中,不打印,抽完时一次性打印(随机)
                在此次抽奖过程中,抽奖箱1共产生了6个奖项.
                    分别为:10,20,100,500,2,300 最高奖项为300元,总计额为932元
                在此次抽奖过程中,抽奖箱2共产生了6个奖项.
                    分别为:5,50,200,800,80,700 最高奖项为800元,总计额为1835元
         */
        ArrayList<Integer> list = new ArrayList<>();
        Collections.addAll(list, 10, 5, 20, 50, 100, 200, 500, 800, 2, 80, 300, 700);
        MyThread t1 = new MyThread(list);
        MyThread t2 = new MyThread(list);

        t1.setName("抽奖箱1");
        t2.setName("抽奖箱2");

        t1.start();
        t2.start();
    }
}
