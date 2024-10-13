package com.study.generics;

public class GenericsDemo {
    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("fuckYou");
        list.add("fuckMe");
        list.add("fuckThey");

        System.out.println(list.get(1));

        System.out.println(list);
    }
}
