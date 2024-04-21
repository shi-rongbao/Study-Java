package com.study.whiledemo;

import java.util.Scanner;

public class Test3 {
    public static void main(String[] args) {
        /*
            需求:给定两个整数,被除数和除数(都是正数,且不超过int的范围)
            将两数相除,要求不能使用乘法,除法和%运算符.
            得到商和余数.
         */
        Scanner sc = new Scanner(System.in);//键盘录入
        // 被除数 / 除数 = 商...余数
        System.out.println("请输入除数:");
        int a = sc.nextInt(); //除数 键盘录入除数
        System.out.println("请输入被除数:");
        int b = sc.nextInt(); //被除数   键盘录入被除数
        int count = 0; //定义一个计数器,计算被除数与除数做了几次减法,得出商的值
        if (b > a) { // 判断被除数与除数哪个大,若被除数大,商大于0,可以做减法
            while (b >= a) {//判断,当被除数作差后记录的数字只要还大于等于a,就代表还能继续做减法,商的值还能再增加,循环继续
                b -= a; // 将被除数与除数a做减法,再将新的值赋给被除数
                count++;//每做一次减法,代表商增加1
            }
            System.out.println("商为:" + count);
            System.out.println("余数为:" + b);
        } else {// 判断被除数与除数哪个大,若除数大,商等于0,不用再做减法,将除数直接作为余数返回,商的值即为0
            System.out.println("商为:" + count);
            System.out.println("余数为:" + a);
        }
    }
}
