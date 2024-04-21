package com.study.treeset;

import java.util.Iterator;
import java.util.TreeSet;

public class TreeSetDemo1 {
    public static void main(String[] args) {
        //创建TreeSet集合对象
        TreeSet<Integer> ts = new TreeSet<>();
        ts.add(2);
        ts.add(1);
        ts.add(5);
        ts.add(3);
        ts.add(4);
        System.out.println(ts);

        Iterator<Integer> it = ts.iterator();
        while (it.hasNext()) {
            Integer i = it.next();
            System.out.println(i);
        }

        System.out.println("=============================");

        for (Integer i : ts) {
            System.out.println(i);
        }

        System.out.println("======================================");

        ts.forEach(i -> System.out.println(i));

    }
}
