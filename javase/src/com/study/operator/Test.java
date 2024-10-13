package com.study.operator;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        //算数运算符
        //加减运算符计算小数时,可能出现运算不准确的现象

        /*
            练习:
                需求:键盘录入一个三位数,将其拆分为个位,十位,百位后打印在控制台
                分析:1.Scanner 键盘录入一个三位数
                    2.个位:num % 10
                      十位:num / 10 % 10
                      百位:num / 10 / 10 % 10
         */
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个三位数:");
        int num = sc.nextInt();
        System.out.println("个位是:" + num % 10);
        System.out.println("十位是:" + num / 10 % 10);
        System.out.println("百位是:" + num / 10 / 10 % 10);


    }
}
