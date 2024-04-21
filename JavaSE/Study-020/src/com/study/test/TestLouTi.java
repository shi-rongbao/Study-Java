package com.study.test;

public class TestLouTi {
    public static void main(String[] args) {
        /*
            小明爬楼梯,有时候一次爬一个台阶,有时候一次爬两个台阶,如果楼梯有100个台阶,小明一共有多少种爬法?
         */
        System.out.println(method(3));
    }

    public static int method(int number) {
        if (number == 1) {
            return 1;
        } else if (number == 2) {
            return 2;
        }
        return method(number - 1) + method(number - 2);
    }
}
