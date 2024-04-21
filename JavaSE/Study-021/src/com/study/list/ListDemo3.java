package com.study.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListDemo3 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        list.add("ddd");
        list.add("eee");
        list.add("fff");

        //迭代器遍历
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String str = it.next();
            if ("bbb".equals(str)){
                it.remove();
            }
            System.out.println(str);
        }

        System.out.println("================================");

        //增强for遍历
        for (String s : list) {
            System.out.println(s);
        }

        System.out.println("================================");

        //lambda表达式遍历
        list.forEach((s) -> System.out.println(s));

        System.out.println("================================");

        //普通for循环遍历
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        System.out.println("================================");

        //列表迭代器遍历
        ListIterator<String> it1 = list.listIterator();
        while (it1.hasNext()){
            String next = it1.next();
            if ("bbb".equals(next)){
                it1.add("abcd");
            }
            System.out.println(next);
        }

        System.out.println("================================");

        for (String s : list) {
            System.out.println(s);
        }
    }
}
