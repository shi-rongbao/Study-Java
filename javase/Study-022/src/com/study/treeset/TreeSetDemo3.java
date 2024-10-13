package com.study.treeset;

import java.util.Comparator;
import java.util.TreeSet;

public class TreeSetDemo3 {
    public static void main(String[] args) {
        TreeSet<String> ts = new TreeSet<>((o1, o2) -> {
            int i = o1.length() - o2.length();
            i = i == 0 ? o1.compareTo(o2) : i;
            return i;
        });

        ts.add("c");
        ts.add("ac");
        ts.add("aaa");
        ts.add("avb");
        ts.add("ad");

        System.out.println(ts);
    }
}
