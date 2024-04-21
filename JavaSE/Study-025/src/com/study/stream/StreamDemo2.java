package com.study.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

public class StreamDemo2 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "a", "b", "c", "d", "e");
        //获取一条流水线,并把集合中的数据放到流水线上
        Stream<String> stream1 = list.stream();
        //使用中介方法,打印流水线上的数据
        stream1.forEach(str -> System.out.println(str));

        list.stream().forEach(str -> System.out.println(str));
    }
}
