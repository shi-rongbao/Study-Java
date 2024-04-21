package com.study.test;

public class Test4 {
    public static void main(String[] args) {
        /*
            需求：
                把一个数组中的元素复制到另一个新的数组中
         */
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        int[] newArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        for (int i = 0; i < newArr.length; i++) {
            System.out.println(newArr[i]);
        }
    }

}
