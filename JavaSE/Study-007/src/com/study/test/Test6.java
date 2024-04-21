package com.study.test;

import java.util.Scanner;

public class Test6 {
    public static void main(String[] args) {
        /*
            需求:
                某系统的数字密码(大于0),比如1983,采用加密方式进行传输.
                规则如下:
                    先得到每位数,然后每位数都加上5,再对10取余,最后将所有数字反转,得到一串新数.
         */
        Scanner sc = new Scanner(System.in); // 创建一个Scanner对象，用于从标准输入读取用户输入
        System.out.println("请输入一个大于0的数字:"); // 打印提示信息，要求用户输入一个大于0的数字
        int number = sc.nextInt(); // 从标准输入读取用户输入的数字，并将其存储在变量number中
        int count = 0; // 初始化变量count为0，用于计算输入数字的位数
        int temp = number; // 将输入数字保存在临时变量temp中

        while (number != 0) {
            number = number / 10; // 通过每次将number除以10来计算输入数字的位数count，并更新number的值
            count++;
        }

        int[] arr = new int[count]; // 根据计算得到的位数count创建一个长度为count的整数数组arr

        for (int i = 0; i < arr.length; i++) {
            int ge = temp % 10; // 通过对temp取模得到输入数字的个位数
            temp /= 10; // 将temp除以10更新为新的值，相当于去掉个位数
            arr[i] = ge; // 将个位数存储在数组arr的对应位置上
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (arr[i] + 5) % 10; // 将数组中的每个元素加上5并取其对10的余数，实现将每个数字加5后取个位数的操作
        }

        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            int temp1 = arr[i]; // 使用双指针技巧，交换数组中的元素
            arr[i] = arr[j];
            arr[j] = temp1;
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]); // 使用循环遍历数组arr，依次输出数组中的每个元素
        }

    }
}
