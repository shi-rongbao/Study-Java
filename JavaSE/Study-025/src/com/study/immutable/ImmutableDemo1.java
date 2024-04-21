package com.study.immutable;

import java.util.List;

public class ImmutableDemo1 {
    public static void main(String[] args) {
        //用List.of方法创建的集合是无法进行修改的,在下面的代码中,只能进行查询操作
        List<String> list = List.of("张三", "李四", "王五", "赵六");
        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(2));
        System.out.println(list.get(3));

        System.out.println("==================");

        list.forEach(str -> System.out.println(str));
    }
}
