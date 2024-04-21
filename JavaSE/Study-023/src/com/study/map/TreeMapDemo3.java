package com.study.map;

import java.util.TreeMap;

public class TreeMapDemo3 {
    public static void main(String[] args) {
        String str = "aababcabcdadwabcgdergjyrtefeshtfd";//需要统计的字符串
        int len = str.length();//获取字符串长度
        TreeMap<Character, Integer> tm = new TreeMap<>();//创建treemap集合
        for (int i = 0; i < len; i++) {//循环
            char c = str.charAt(i);//获取当前字符
            if (tm.containsKey(c)) {//如果当前字符已经在集合中出现过则进入if语句
                int value = tm.get(c);//获得到字符所对应的value
                value++;//value自增一次,代表次数加一
                tm.put(c, value);//覆盖原来的value值
            } else {//如果第一次出现
                tm.put(c, 1);//添加当前字符和1
            }
        }
        tm.forEach((key, value) -> System.out.print(key + "(" + value + ")"));

        StringBuilder sb = new StringBuilder();//创建StringBuilder对象
        tm.forEach((key, value) -> {//lamb表达式遍历tm集合
            //依次添加 "key" "(" "value" ")"
            sb.append(key);
            sb.append("(");
            sb.append(value);
            sb.append(")");
        });
        System.out.println();
        System.out.println("===========================");//华丽的分割线

        System.out.println(sb);
    }
}
