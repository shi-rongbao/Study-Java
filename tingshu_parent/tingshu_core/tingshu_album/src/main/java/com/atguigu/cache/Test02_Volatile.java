package com.atguigu.cache;

import com.atguigu.util.SleepUtils;

/**
 * @author ShiRongbao
 * @create 2024/6/11 18:12
 * @description:
 */
public class Test02_Volatile {
    private volatile static Integer flag = 1;

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("我是子线程工作内存的flag的值:" + flag);
            while (flag == 1) {

            }
            System.out.println("子线程操作结束....:" + flag);
        }).start();
        SleepUtils.millis(500);
        flag = 2;
        System.out.println("over:" + flag);
    }
}
