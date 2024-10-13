package com.study.looptest;

public class Test1 {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            if (i == 2) {
                continue;
            }
            System.out.println("老虎在吃第" + (i + 1) + "个包子");
        }
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        for (int i = 0; i < 5; i++) {
            System.out.println("老虎在吃第" + (i + 1) + "个包子");
            if (i == 2) {
                break;
            }
        }
    }
}
