package com.study.function;

import java.util.Arrays;

public class FunctionDemo1 {
    public static void main(String[] args) {
        //  需求:创建一个数组,进行倒序排列

        Integer[] arr = {3, 5, 4, 1, 6, 2};

        //匿名内部类
//        Arrays.sort(arr, new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o2 - o1;
//            }
//        });
//
//        for (int num : arr) {
//            System.out.print(num + " ");
//        }

        //lambda表达式
//        Arrays.sort(arr,(o1, o2) -> o2 - o1);
//
//        for (int num : arr) {
//            System.out.print(num + " ");
//        }

        //方法引用
        Arrays.sort(arr, FunctionDemo1::subtraction);

        for (int num : arr) {
            System.out.print(num + " ");
        }
    }

    public static int subtraction(int num1, int num2) {
        return num2 - num1;
    }
}
