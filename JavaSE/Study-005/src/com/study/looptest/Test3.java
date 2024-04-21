package com.study.looptest;

import java.util.Scanner;

public class Test3 {
    public static void main(String[] args) {
        /*
            需求:键盘录入一个大于等于2的整数X,计算并返回X的平方根.
            结果只保留整数部分,小数部分将被舍去
         */
        //1.键盘录入一个整数
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个大于等于2的整数");
        int num = sc.nextInt();
        if (num >= 2) {
            int i = 1;
            while (true) {
                if (i * i > num) {
                    System.out.println("结果为" + (i - 1));
                    break;
                } else if (i * i == num) {
                    System.out.println("结果为" + i);
                    break;
                }
                i++;
            }
        } else System.out.println("你输入的数字不符合要求!");
    }
}
