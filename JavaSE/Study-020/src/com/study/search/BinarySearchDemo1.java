package com.study.search;

public class BinarySearchDemo1 {
    public static void main(String[] args) {
        /*
            需求:
                利用二分查找查询某个元素在数组中的索引
         */

        int[] arr = {1, 6, 9, 13, 23, 27, 31, 39, 40, 45, 49, 52, 58, 60, 61, 68, 77, 89, 92, 93, 102, 108};
        int index = binarySearch(arr, 41);
        System.out.println(index);
    }

    public static int binarySearch(int[] arr, int number) {
        int min = 0;
        int max = arr.length - 1;
        if (number > arr[arr.length - 1] || number < arr[0]) {
            return -1;
        }
        while (true) {
            int mid = (min + max) / 2;
            if (min > max) {
                return -1;
            }
            if (number > arr[mid]) {
                min = mid + 1;
            } else if (number < arr[mid]) {
                max = mid - 1;
            }
            if (number == arr[mid]) {
                return mid;
            }
        }
    }
}
