package com.study.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CollectionDemo3 {
    public static void main(String[] args) {
        Collection<String> coll = new ArrayList<>();
        coll.add("aaa");
        coll.add("bbb");
        coll.add("ccc");
        coll.add("ddd");
        coll.add("eee");
        coll.add("fff");
        coll.add("ggg");
        Iterator<String> it = coll.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }
}
