package com.study.collection;

import java.util.ArrayList;
import java.util.Collection;

public class CollectionDemo4 {
    public static void main(String[] args) {
        Collection<String> coll = new ArrayList<>();
        coll.add("aaa");
        coll.add("bbb");
        coll.add("ccc");
        coll.add("ddd");
        for (String s : coll){
            System.out.println(s);
        }
        for (String s : coll) {
            System.out.println(s);
        }
    }
}
