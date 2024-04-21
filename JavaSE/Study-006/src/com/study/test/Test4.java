package com.study.test;

public class Test4 {
    public static void main(String[] args) {
        int[] arr = {11, 55, 232, 892, 146};
        int from = 1;
        int to = 3;
        int[] ints = copyOfRange(arr, from, to);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }

    public static int[] copyOfRange(int[] arr, int from, int to) {
        int[] arrResult = new int[(to - from)];
        for (int i = 0; i < arr.length; i++) {
            if (from < to){
                arrResult[i] = arr[from];
                from++;
            }
        }
        return arrResult;
    }
}
