package com.study.array;

public class ArrayTest3 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int start = 0;
        int end = arr.length - 1;
        int temp = arr[0];
        for (int i = 0; i < (arr.length - 1) / 2; i++) {
            if (start <= end) {
                temp = arr[start];
                arr[start] = arr[end];
                arr[end] = temp;
                start++;
                end--;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        System.out.println("===========================");
        int[] arr1 = {1, 2, 3, 4, 5};
        for (int i = 0, j = arr1.length - 1; i < j; i++, j--){
            int temp1 = arr1[i];
            arr1[i] = arr1[j];
            arr1[j] = temp1;
        }
        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i] + " ");
        }
    }
}
