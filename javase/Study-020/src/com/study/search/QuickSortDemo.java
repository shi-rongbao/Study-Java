package com.study.search;

import java.util.Random;

public class QuickSortDemo {
    public static void main(String[] args) {
        //int[] arr = {6, 1, 2, 7, 9, 3, 4, 5, 10, 8};
        int[] arr = new int[100000];
        Random r = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = r.nextInt();
        }
        long start = System.currentTimeMillis();
        quickSort(arr, 0, arr.length - 1);
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }

    public static void quickSort(int[] arr, int i, int j) {
        int start = i;
        int end = j;
        if (start > end){
            return;
        }
        int baseNumber = arr[i];
        while (start != end) {
            while (true) {
                if (end <= start || arr[end] < baseNumber) {
                    break;
                }
                end--;
            }

            while (true) {
                if (end <= start || arr[start] > baseNumber) {
                    break;
                }
                start++;
            }

            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
        }

        int temp = arr[i];
        arr[i] = arr[start];
        arr[start] = temp;

        quickSort(arr, i, start - 1);
        quickSort(arr, start + 1,j);

    }
}
