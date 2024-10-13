package com.study.generics;

import java.util.ArrayList;

public class GenericsDemo2 {
    public static void main(String[] args) {
        ArrayList<String> list1 = new ArrayList<>();
        ListUtil.addAll(list1,"z","h","a","n");
        System.out.println(list1);


        ArrayList<Integer> list2 = new ArrayList<>();
        ListUtil.addAll(list2,33,22,11,0);
        System.out.println(list2);
    }
}
