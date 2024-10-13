package com.study.map;

import java.util.TreeMap;

public class TreeMapDemo1 {
    public static void main(String[] args) {
        TreeMap<Integer, String> tm = new TreeMap<>((o1, o2) -> o2 - o1);
        tm.put(3, "百事可乐");
        tm.put(5, "果粒橙");
        tm.put(2, "可口可乐");
        tm.put(4, "尖叫");
        tm.put(1, "农夫山泉");

        System.out.println(tm);

        tm.forEach((integer, s) -> System.out.println(s));

    }
}
