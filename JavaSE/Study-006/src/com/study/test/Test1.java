package com.study.test;

public class Test1 {
    public static void main(String[] args) {
        //需求: 设计一个方法用于数组遍历,要求遍历的结果是在一行上的.
        //     例如:[11,22,33,44,55]
        int[] arr = {55, 22, 11, 33, 44};
        printArr(arr);
    }

    public static void printArr(int[] arr) {
        System.out.print("[" + arr[0] + ",");
        for (int i = 1; i < arr.length - 1; i++) {
            System.out.print(arr[i] + ",");
        }
        System.out.print(arr[arr.length - 1] + "]");
    }
}
