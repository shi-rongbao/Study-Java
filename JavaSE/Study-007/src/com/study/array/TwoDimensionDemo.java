package com.study.array;

public class TwoDimensionDemo {
    public static void main(String[] args) {
        int[] arr1 = {11, 22, 55, 66, 77};
        int[] arr2 = {33, 44, 65, 23};

        int[][] arrs = new int[3][3];
        arrs = new int[][]{
                {1, 3, 4},
                {2, 3, 4},
                {5, 3, 2}
        };


        int[][] arr = {
                arr1,
                arr2
        };
        for (int i = 0; i < arr.length; i++) {
            for (int i1 = 0; i1 < arr[i].length; i1++) {
                System.out.print(arr[i][i1] + " ");
            }
            System.out.println();
        }
    }
}
