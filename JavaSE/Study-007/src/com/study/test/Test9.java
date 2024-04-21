package com.study.test;

import java.util.Random;

public class Test9 {
    public static void main(String[] args) {
        int[] arr = {2, 588, 888, 1000, 10000};
        Random r = new Random();
        for (int i = 0; i < arr.length; i++) {
            int index = r.nextInt(5);
            int temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
