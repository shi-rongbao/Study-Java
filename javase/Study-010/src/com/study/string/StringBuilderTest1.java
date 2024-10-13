package com.study.string;

import java.util.Scanner;

public class StringBuilderTest1 {
    public static void main(String[] args) {
        /*
            需求:键盘接受一个字符串,程序判断出该字符串是否是对称字符串,并在控制台打印是或不是
            对称字符串:123321、111
            非对称字符串:123123
         */

        // 创建一个StringBuilder对象，用于存储输入的字符串
        StringBuilder sb = new StringBuilder();

        // 创建一个Scanner对象，用于接收用户输入
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个字符串:");

        // 从用户输入中获取一个字符串，并追加到StringBuilder对象中
        sb.append(sc.next());

        // 将StringBuilder对象转换为String类型，用于比较
        String str1 = sb.toString();

        // 将StringBuilder对象进行反转，再转换为String类型
        String str2 = sb.reverse().toString();

        // 比较原始字符串和反转后的字符串是否相等
        if (str1.equals(str2)) {
            System.out.println("是");
        } else {
            System.out.println("不是");
        }
    }
}
