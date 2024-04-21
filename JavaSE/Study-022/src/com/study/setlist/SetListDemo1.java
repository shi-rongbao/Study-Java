package com.study.setlist;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SetListDemo1 {
    public static void main(String[] args) {
        /*
            利用Set系列的集合,添加字符串,并使用多种方式遍历
         */
        Set<String> set = new HashSet<>();
        set.add("aaa");
        set.add("bbb");
        set.add("ccc");
        set.add("ddd");

        for (String s : set) {
            System.out.println(s);
        }

        System.out.println("===========================");

        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            String str = it.next();
            System.out.println(str);
        }

        System.out.println("===========================");

        set.forEach(s -> System.out.println(s));

        System.out.println("===========================");

        System.out.println(8 & 2);
    }
}
