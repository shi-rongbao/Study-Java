package com.study.whiledemo;

import java.util.Scanner;

public class Test2 {
    public static void main(String[] args) {
        /*
            需求:如果x是一个回文整数,打印true,否则,返回false.
            解释:回文数是指正序(从左到右)和倒序(从右到左)读都是一样的整数
            例如:123不是 121是 123321是
         */
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个数字:");
        int num = sc.nextInt();
        int temp = num;
        int number = 0;
        while (num != 0) {
            int ge = num % 10;
            num /= 10;

            number = number * 10 +ge;
        }
        System.out.println(number == temp);
    }
}
