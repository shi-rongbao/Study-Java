package com.study.string;

public class StringTest14 {
    public static void main(String[] args) {
        /*
            需求:
                给定两个以字符串形式表示的非负整数num1和num2,返回num1和num2的乘积,它们的乘积也表示为字符串形式
         */

        String num1 = "43213677";
        String num2 = "433677";

        // 将字符串num1和num2转换为字符数组
        char[] char1 = num1.toCharArray();
        char[] char2 = num2.toCharArray();

        // 创建两个整型数组，用于存储字符数组中的数字
        int[] arr1 = new int[char1.length];
        int[] arr2 = new int[char2.length];

        // 将字符数组char1中的数字转换为整数，并计算出num1的整数值
        int number1 = 0;
        for (int i = 0; i < char1.length; i++) {
            arr1[i] = char1[i] - 48; // 将字符转换为对应的数字
            number1 = number1 * 10 + arr1[i]; // 计算整数值
        }

        // 将字符数组char2中的数字转换为整数，并计算出num2的整数值
        int number2 = 0;
        for (int i = 0; i < char2.length; i++) {
            arr2[i] = char2[i] - 48; // 将字符转换为对应的数字
            number2 = number2 * 10 + arr2[i]; // 计算整数值
        }

        // 计算num1和num2的乘积
        int number = number1 * number2;

        // 将乘积转换为字符串
        String str = "";
        str = str + number;

        // 打印乘积字符串
        System.out.println(str);
    }
}
