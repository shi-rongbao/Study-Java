package com.study.test;

import java.util.Scanner;

public class Test3 {
    public static void main(String[] args) {
        int[] arr = {11, 4545, 25, 654, 1645, 9, 7};
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你要查询的数字:");
        System.out.println(getIndex(arr, sc.nextInt()));
    }

    public static boolean getIndex(int[] arr, int num) {

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num) {
                return true;
            }
        }
        return false;
    }
}
