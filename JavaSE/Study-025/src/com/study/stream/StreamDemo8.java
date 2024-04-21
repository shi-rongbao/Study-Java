package com.study.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamDemo8 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "张无忌-男-15", "周芷若-女-14", "赵敏-女-13", "张强-男-20", "张三丰-男-100", "张翠三-男-40", "张亮-男-35", "王二麻子-男-37", "谢广坤-男-41");

        //收集信息到list集合中
        //需求:
        //所有男性收集起来

//        List<String> newList = list.stream().filter(s -> "男".equals(s.split("-")[1])).collect(Collectors.toList());
//        System.out.println(newList);

        //toMap的两个参数:
        //  参数1:键的生成规则
        //  参数2:值的生成规则
        Map<String, Integer> map1 = list.stream().filter(s -> "男".equals(s.split("-")[1])).collect(
                Collectors.toMap(new Function<String, String>() {
                    //键的泛型:
                    //  泛型1:流中的数据类型
                    //  泛型2:要添加的键的数据类型
                    @Override
                    public String apply(String s) {
                        //s代表流中的每一个数据
                        return s.split("-")[0];
                    }
                    //值的泛型:
                    //  泛型1:流中的数据类型
                    //  泛型2:要添加的键的数据类型
                }, new Function<String, Integer>() {
                    @Override
                    public Integer apply(String s) {
                        //s代表流中的每一个数据
                        return Integer.parseInt(s.split("-")[2]);
                    }
                }));

        Map<String, Integer> map2 = list.stream()
                .filter(s -> "男".equals(s.split("-")[1]))
                .collect(Collectors.toMap(s -> s.split("-")[0], s -> Integer.parseInt(s.split("-")[2])));

        map1.forEach((name, age) -> System.out.println(name + " = " + age));
        map2.forEach((name, age) -> System.out.println(name + " = " + age));

    }
}
