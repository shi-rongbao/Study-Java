package com.study.search;

public class BasicSearchDemo1 {
    public static void main(String[] args) {
        /*
            需求:
                定义一个方法利用基本查找,查询某个元素是否存在
                数据如下:{131,127,147,81,103,23,7,79}
         */
        int[] arr = {131, 127, 147, 81, 103, 23, 7, 79};
        System.out.println(getNumber(arr, 7));
    }

    public static boolean getNumber(int[] arr, int number) {
        for (int i = 0; i < arr.length; i++) {
            if (number == arr[i]){
                return true;
            }
        }
        return false;
    }
}
