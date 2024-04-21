package com.study.array;

import java.util.ArrayList;

public class ArrayListDemo1 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        System.out.println(list);
        list.add("fuck");
        list.add("you");
        list.add("and");
        list.add("they");
        System.out.println(list);
        list.remove(0);
        System.out.println(list);
        list.set(0,"fuckYou");
        System.out.println(list);
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
        }
    }
}
