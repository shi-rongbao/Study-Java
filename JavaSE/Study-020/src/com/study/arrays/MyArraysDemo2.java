package com.study.arrays;

import java.util.Arrays;
import java.util.Comparator;

public class MyArraysDemo2 {
    public static void main(String[] args) {
        Integer[] arr = {5, 2, 7, 1, 3, 9, 4, 6, 8};
        Arrays.sort(arr, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        System.out.println(Arrays.toString(arr));
    }
}
