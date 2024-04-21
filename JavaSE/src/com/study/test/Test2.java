package com.study.test;

import java.util.Scanner;

public class Test2 {
    public static void main(String[] args) {
        /*
            需求: 键盘录入两个整数,只要两个整数中有6或者两个整数的和为6的倍数,输出true
            否则输出false
         */
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入第一个整数:");
        int num1 = sc.nextInt();
        System.out.println("请输入第二个整数:");
        int num2 = sc.nextInt();
        if (num1 == 6 || num2 == 6 || (num1 + num2) % 6 == 0){
            System.out.println(true);
        }
         else System.out.println(false);
    }
}
