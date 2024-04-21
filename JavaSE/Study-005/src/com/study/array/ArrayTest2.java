package com.study.array;

import java.util.Random;

public class ArrayTest2 {
    public static void main(String[] args) {
        /*
            需求: 生成10个1-100之间的随机数存入数组.
                1).求出所有的数据和
                2).求出所有数据的平均值
                3).统计有多少个数据比平均值小
         */
        int[] arr = new int[10];
        Random r = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = r.nextInt(100) + 1;
            System.out.print(arr[i]);
            System.out.print(',');
        }
        int sum = getSum(arr);
        int ave = sum / arr.length;
        int count = getCount(arr, ave);
        System.out.println();
        System.out.println("所有数据和为:" + sum);
        System.out.println("平均数为:" + ave);
        System.out.println("比平均数小的数的个数为:" + count);
    }

    private static int getCount(int[] arr, int avg) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (avg > arr[i]) {
                count++;
            }
        }
        return count;
    }

    private static int getSum(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }
}
