package com.study.stream;

import java.util.ArrayList;
import java.util.Collections;

public class StreamDemo6 {
    public static void main(String[] args) {
        /*
            filter      过滤
            limit       获取前几个元素
            skip        跳过前几个元素

            注意1:中间方法,返回新的Stream流,原来的Stream流只能使用一次,建议使用链式编程
            注意2:修改Stream流中的数据,不会影响原来集合或者数组中的数据
         */

        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "张无忌", "周芷若", "赵敏", "张强", "张三丰", "张翠山", "张亮", "王二麻子", "谢广坤");


        //filter        过滤
        list.stream().
                filter(name -> name.startsWith("张")).
                forEach(name -> System.out.println(name));

        System.out.println("============================");

        //limit       获取前几个元素
        list.stream().limit(5).forEach(name -> System.out.println(name));

        System.out.println("============================");

        //skip        跳过前几个元素
        list.stream().skip(4).forEach(name -> System.out.println(name));

        System.out.println("============================");

        list.stream().limit(6).skip(3).forEach(name -> System.out.println(name));

    }
}
