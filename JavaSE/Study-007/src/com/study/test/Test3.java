package com.study.test;

import java.util.Random;

public class Test3 {
    public static void main(String[] args) {
        /*
            需求:
                定义方法实现随机产生一个5位数验证码
                验证码格式:
                长度为5
                前四位是大写字母或小写字母
                最后一位是数字
         */
        for (int i = 0; i < 100; i++) {
            getYanZhengMa();
        }
    }

    private static void getYanZhengMa() {
        char[] arr = new char[52];
        for (int i = 0; i < 26; i++) {
            arr[i] = (char) (i + 65);
        }
        for (int i = 0; i < 26; i++) {
            arr[i + 26] = (char) (i + 97);
        }
        Random r = new Random();
        for (int i = 0; i < 4; i++) {
            int num = r.nextInt(arr.length);
            System.out.print(arr[num]);
        }
        int num = r.nextInt(10);
        System.out.println(num);
    }
}
