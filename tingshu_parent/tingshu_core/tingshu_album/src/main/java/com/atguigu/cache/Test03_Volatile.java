package com.atguigu.cache;

import com.atguigu.util.SleepUtils;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ShiRongbao
 * @create 2024/6/11 18:21
 * @description:
 */
public class Test03_Volatile {
    private volatile Integer num = 0;

    AtomicInteger a = new AtomicInteger(0);
    public Integer getNum() {
        return num;
    }

    public Integer incr() {
        return ++num;
    }

    public static void main(String[] args) {
        Test03_Volatile dataOne = new Test03_Volatile();
        for (int i = 0; i < 1000; i++) {
            new Thread(dataOne::incr).start();
        }
        SleepUtils.millis(500);
        System.out.println(dataOne.getNum());
    }
}
