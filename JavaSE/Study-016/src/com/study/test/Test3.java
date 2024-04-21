package com.study.test;

import java.util.Random;

public class Test3 {
    public static void main(String[] args) {
        //另一种填充二维数组的方法
    }
//        int[][] arr = new int[4][4];
//        int x = 0;
//        int y = 0;
//        int[] tempArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
//        Random r = new Random();
//        for (int i = 0; i < tempArr.length; i++) {
//            int index = r.nextInt(tempArr.length);
//            int temp = tempArr[i];
//            tempArr[i] = tempArr[index];
//            tempArr[index] = temp;
//        }
//
//        for (int i = 0; i < tempArr.length; i++) {
//            System.out.print(tempArr[i] + " ");
//        }
//        System.out.println();
//
//        int number = 0;
//        for (int i = 0; i < arr.length; i++) {
//            for (int j = 0; j < arr[i].length; j++) {
//                if (tempArr[number] == 0) {
//                    x = number / 4;
//                    y = number % 4;
//                } else {
//                    arr[i][j] = tempArr[number];
//                }
//                number++;
//            }
//        }
//        for (int i = 0; i < arr.length; i++) {
//            for (int j = 0; j < arr[i].length; j++) {
//                System.out.print(arr[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println(x + "@" + y);
//    }
}
