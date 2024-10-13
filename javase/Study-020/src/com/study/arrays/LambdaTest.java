package com.study.arrays;

import java.util.Arrays;

public class LambdaTest {
    public static void main(String[] args) {
        /*
            需求:
                定义数组并且存储一些字符串,利用Arrays中的sort方法进行排序
                按照字符串的长度进行排序,短的在前面,长的在后面
         */

        String[] arr = {"12345", "1234567", "123", "123456", "1234"};

        Arrays.sort(arr, (o1, o2) -> o1.length() - o2.length());

        System.out.println(Arrays.toString(arr));

    }
}
