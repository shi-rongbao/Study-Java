package com.study.switchdemo;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        /*
            需求:
                键盘录入星期数,输出工作日,休息日
         */
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入今天是星期几:");
        int week = sc.nextInt();
        switch (week) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                System.out.println("工作日");
                break;
            case 6:
            case 7:
                System.out.println("休息日");
                break;
            default:
                System.out.println("输入有误");
        }

        for (int i = 0; i < 5; i++) {
            System.out.println("HelloWord");
        }
    }
}
