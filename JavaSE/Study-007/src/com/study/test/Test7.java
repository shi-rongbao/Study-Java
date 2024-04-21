package com.study.test;

public class Test7 {
    public static void main(String[] args) {
        // 调用jieMi方法并将返回的数组存储在"result"变量中
        int result = jieMi(8346);
        System.out.println(result);
    }

    public static int jieMi(int num) {
        int count = 0;
        int temp = num;

        // 计算给定数字的位数
        while (num != 0) {
            num /= 10;
            count++;
        }

        // 创建一个数组以存储数字的每个位数
        int[] arr = new int[count];
        int index = arr.length - 1;

        // 提取每个位数的数字并将其存储在数组中
        for (int i = 0; i < arr.length; i++) {
            arr[index] = temp % 10;
            temp /= 10;
            index--;
        }

        // 反转数组以恢复数字的原始顺序
        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            int temp1 = arr[i];
            arr[i] = arr[j];
            arr[j] = temp1;
        }

        // 数字对10取模的反转
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= 5 && arr[i] <= 9) {
                // 如果数字在5到9之间，则保持不变
                arr[i] = arr[i];
            } else {
                // 如果数字小于5，则加上10
                arr[i] = arr[i] + 10;
            }
        }

        // 每个数字减去5
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] - 5;
        }
        int number = 0;
        //将数组中的数字拼接起来
        for (int i = 0; i < arr.length; i++) {
            number = number * 10 + arr[i];
        }

        // 返回结果数组
        return number;
    }
}
