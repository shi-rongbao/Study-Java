package com.study.arrays;

import java.util.Arrays;

public class LambdaDemo1 {
    public static void main(String[] args) {
        Integer[] arr = {2, 3, 1, 5, 6, 7, 8, 4, 9, 0};

        Arrays.sort(arr, (Integer o1, Integer o2) -> {
                    return o1 - o2;
                }
        );
        Arrays.sort(arr, (Integer o1, Integer o2) -> {
            return o1 - o2;
        });
    }
}
