package com.study.string;

public class StringTest4 {
    public static void main(String[] args) {
        /*
            需求:
                定义一个方法,吧int数组中的数据按照指定的格式拼接成一个字符串返回,
                调用该方法,并在控制台输出结果.
                例如:
                    数组为:int[] arr = {1,2,3};
                    执行方法后的输出结果为:[1,2,3]
         */
        int[] arr = {1, 2, 3};
        String result = arrToString(arr);
        System.out.println(result);

    }

    public static String arrToString(int[] arr) {
        if (arr == null) {
            return "";
        }
        if (arr.length == 0) {
            return "[]";
        }
        String result = "[";
        for (int i = 0; i < arr.length - 1; i++) {
            result += (arr[i] + ",");
        }
        result += (arr[arr.length - 1] + "]");

        return result;
    }
}
