package com.study.string;

import java.util.StringJoiner;

public class StringBuilderTest2 {
    public static void main(String[] args) {
        /*
            需求:
                定义一个方法,把int数组中的数据按照指定的格式拼接成一个字符串返回.
                调用该方法,并在控制台输出结果.
                例如:数组为int[] arr = {1,2,3};
                执行方法后的输出结果为:[1,2,3]
         */

        // 定义一个整型数组
        int[] arr = {1, 2, 3, 4, 5, 6, 7};

        // 调用method方法，将数组拼接为指定格式的字符串
        String result = method(arr);

        // 输出结果
        System.out.println(result);
    }

    /**
     * 将int数组按照指定格式拼接成字符串
     * @param arr 待拼接的int数组
     * @return 拼接后的字符串
     */
    public static String method(int[] arr) {
        // 创建一个StringBuilder对象，用于拼接字符串
        StringBuilder sb = new StringBuilder("[");

        // 遍历数组，将数组元素按照指定格式追加到StringBuilder对象中
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1) {
                // 如果是数组的最后一个元素，则直接追加到StringBuilder对象中，并添加"]"作为结尾
                sb.append(arr[i]).append("]");
            } else {
                // 如果不是数组的最后一个元素，则追加到StringBuilder对象中，并添加","作为分隔符
                sb.append(arr[i]).append(",");
            }
        }

        // 将StringBuilder对象转换为String类型
        String result = sb.toString();

        // 返回拼接后的字符串
        return result;
    }
}
