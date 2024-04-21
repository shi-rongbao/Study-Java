package com.study.exception;

public class ExceptionDemo3 {
    public static void main(String[] args) {
        int[] arr = {7, 3, 8, 2, 1, 0};
        int[] arr1 = {};

        int max = 0;
        try {
            max = getMax(arr1);
        } catch (NullPointerException e) {
            System.out.println("空指针异常");
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("数组索引越界异常");
        }
    }

    public static int getMax(int[] arr) {
        if (arr == null) {
            throw new NullPointerException();
        }

        if (arr.length == 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }
}
