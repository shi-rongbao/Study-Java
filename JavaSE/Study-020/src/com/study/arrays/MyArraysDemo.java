package com.study.arrays;

import java.util.Arrays;

public class MyArraysDemo {
    public static void main(String[] args) {
        int[] arr = {5, 2, 7, 1, 3, 9, 4, 6, 8};

        System.out.println(Arrays.toString(arr));

        System.out.println("=======================================");

        System.out.println(Arrays.binarySearch(arr, 3));
        System.out.println(Arrays.binarySearch(arr, 10));

        System.out.println("=======================================");

        int[] copy = Arrays.copyOf(arr, 11);
        System.out.println(Arrays.toString(copy));

        System.out.println("=======================================");

        int[] copyOfRange = Arrays.copyOfRange(arr, 1, 5);//包头布包围
        System.out.println(Arrays.toString(copyOfRange));

        System.out.println("=======================================");

        int[] arr1 = new int[10];
        Arrays.fill(arr1,2);
        System.out.println(Arrays.toString(arr1));

        System.out.println("=======================================");

        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
