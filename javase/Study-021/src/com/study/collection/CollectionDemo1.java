package com.study.collection;

import java.util.ArrayList;
import java.util.Collection;

public class CollectionDemo1 {
    public static void main(String[] args) {
        //Collection是一个接口
        //Arraylist 是 Collection的一个实现类

        Collection<String> coll = new ArrayList<>();
        coll.add("a");
        coll.add("b");
        coll.add("c");
        System.out.println(coll);

        //coll.clear();
        //System.out.println(coll);

        coll.remove("a");
        System.out.println(coll);

        boolean remove = coll.remove(0);//编译看左边,运行看右边  接口总没有int类型的重载
        System.out.println(remove);
        System.out.println(coll);

        System.out.println(coll.contains("a"));
    }
}
