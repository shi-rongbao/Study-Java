package com.study.function;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class FunctionDemo5 {
    public static void main(String[] args) {
        /*
            方法引用(类名引用成员方法)
            格式
                类名 :: 成员方法
            需求:
                集合里面一些字符串,要求变成大写后进行输出
         */

        //创建集合对象
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "aaa", "bbb", "ccc", "ddd");

        //匿名内部类
//        List<String> newList1 = list.stream().map(new Function<String, String>() {
//            @Override
//            public String apply(String s) {
//                return s.toUpperCase();
//            }
//        }).toList();
//
//        System.out.println(newList1);

        //方法引用
        List<String> newList2 = list.stream().map(String::toUpperCase).toList();
        System.out.println(newList2);
    }
}
