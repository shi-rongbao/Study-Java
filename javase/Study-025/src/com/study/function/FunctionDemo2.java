package com.study.function;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FunctionDemo2 {
    public static void main(String[] args) {
        /*
            练习:
                集合中有以下数字,要求把他们都变成int类型
                " 1 " , " 2 " , " 3 " , " 4 " , " 5 "
         */
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "1", "2", "3", "4", "5");

        //匿名内部类
        list.stream().map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return Integer.parseInt(s);
            }
        }).forEach(System.out :: println);

        //方法引用
        list.stream()
                .map(Integer::parseInt)
                .forEach(System.out :: println);
    }
}
