package com.study.list;

import java.util.ArrayList;
import java.util.List;

public class ListDemo1 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        list.add("ddd");
        list.add(1,"qqq");
        String remove = list.remove(0);
        System.out.println(remove);
        String mmm = list.set(0, "mmm");
        System.out.println(mmm);
        String s = list.get(3);
        System.out.println(s);
        System.out.println(list);
    }
}
