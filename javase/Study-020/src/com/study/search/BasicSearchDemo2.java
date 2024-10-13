package com.study.search;

import java.util.ArrayList;

public class BasicSearchDemo2 {
    public static void main(String[] args) {
        /*
        需求:
             定义一个方法利用基本查找,查询某个元素在数组中的索引,不考虑数组中的重复元素
             数据如下:{131,127,147,81,103,,81,23,7,79}
        */

        int[] arr = {131, 127, 147, 81, 103, 81, 23, 7, 79};
        int number = 81;
        ArrayList<Integer> list = basicSearch(arr, number);
        System.out.println(list);

    }

    public static ArrayList<Integer> basicSearch(int[] arr, int number) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == number){
                list.add(i);
            }
        }
        return list;
    }
}
