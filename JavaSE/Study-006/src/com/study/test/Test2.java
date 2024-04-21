package com.study.test;

public class Test2 {
    public static void main(String[] args) {
        int[] arr = {11, 66, 22, 33, 77, 55, 33, 13};
        System.out.println(getArrMax(arr));
        int arrMax = getArrMax(arr);
    }

    public static int getArrMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }
}
