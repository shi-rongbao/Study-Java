package com.study.stream;

import java.util.Arrays;

public class StreamDemo4 {
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5, 6, 7};
        String[] arr2 = {"fuck", "my", "roommate"};
        Arrays.stream(arr1).forEach(i -> System.out.println(i));
        Arrays.stream(arr2).forEach(str -> System.out.println(str));
    }
}
