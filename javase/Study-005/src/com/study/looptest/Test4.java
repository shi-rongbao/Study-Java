package com.study.looptest;

import java.util.Scanner;

public class Test4 {
    public static void main(String[] args) {
        /*
            需求:
                键盘录入一个整数,判断该整数是否为一个质数
         */
        //1.Scanner 键盘录入
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个整数:");
        int num = sc.nextInt();
        boolean flag = true;
        if (num >= 1) {
            for (int i = 2; i < num; i++) {
                if (num % i == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag){
                System.out.println(num + "是质数");
            }else System.out.println(num + "不是质数");
        }
    }
}
