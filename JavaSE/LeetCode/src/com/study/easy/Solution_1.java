package com.study.easy;

import java.util.Arrays;

public class Solution_1 {
    public static void main(String[] args) {
        /*
            给定一个整数数组nums和一个整数目标值target，请你在该数组中找出和为目标值target的那两个整数，并返回它们的数组下标。
            你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
            你可以按任意顺序返回答案。
         */
        int[] num = {1, 3, 4, 2};
        int target = 6;

        int[] arr = twoSum(num, target);

        String result = Arrays.toString(arr);
        System.out.println(result);
    }

    public static int[] twoSum(int[] num, int target) {
        int[] arr = new int[2];

        int temp;
        lo:
        for (int i = 0; i < num.length; i++) {
            for (int j = 1; j < num.length; j++) {
                temp = num[i] + num[j];
                if (temp == target) {
                    arr[0] = i;
                    if (j == i) {
                        j++;
                        arr[1] = j;
                    }else {
                        arr[1] = j;
                    }
                    break lo;
                }
            }
        }
        return arr;
    }
}
