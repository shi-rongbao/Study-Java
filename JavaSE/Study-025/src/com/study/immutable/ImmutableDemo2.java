package com.study.immutable;

import java.util.Iterator;
import java.util.Set;

public class ImmutableDemo2 {
    public static void main(String[] args) {
        //set集合是存取无序的

        //set集合里面的元素是唯一的
        //如果用Set.of方法创建数组,必须保证添加的元素是不重复的,否则报错: IllegalArgumentException

        Set<String> set = Set.of("fuck", "my", "roommate");

        set.forEach(s -> System.out.println(s));

        System.out.println("============================");

        for (String s : set) {
            System.out.println(s);
        }

        System.out.println("============================");

        Iterator<String> it = set.iterator();
        while (it.hasNext()){
            String s = it.next();
            System.out.println(s);
        }

        System.out.println("============================");

        //set.remove("fuck");
    }
}
