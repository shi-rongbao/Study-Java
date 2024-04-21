package com.study.test;

import java.util.Random;

public class Test {
    public static void main(String[] args) {
        /*
            需求:打乱一维数组中的数据,并按照4个一组的方式添加到二维数组中


        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        Random r = new Random();
        for (int i = 0; i < arr.length; i++) {
            int index = r.nextInt(arr.length);
            int temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        int[][] arr2 = new int[4][4];
        int number = 0;
        for (int i = 0; i < arr2.length; i++) {
            for (int j = 0; j < arr2[i].length; j++) {
                arr2[i][j] = arr[number];
                number++;
            }
        }

         */
    }
}
