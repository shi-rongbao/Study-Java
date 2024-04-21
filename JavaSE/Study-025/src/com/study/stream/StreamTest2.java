package com.study.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamTest2 {
    public static void main(String[] args) {
        /*
            数据操作
                练习:
                创建一个ArrayList集合,并添加以下字符串,字符串前面是姓名,后面是年龄
                "zhangsan"23
                "lisi",24
                "wangwu"25
                保留年龄大于等于24岁的人,并且将结果收集到Map集合中,姓名为键,年龄为值
         */
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list,"zhangsan,23","lisi,24","wangwu,25");
        Map<String, Integer> map =
                list.stream()
                .filter(str -> Integer.parseInt(str.split(",")[1]) >= 24)
                .collect(Collectors.toMap(
                        str -> str.split(",")[0],
                        str -> Integer.parseInt(str.split(",")[1])));
        System.out.println(map);
    }
}
