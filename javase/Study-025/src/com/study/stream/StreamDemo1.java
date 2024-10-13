package com.study.stream;

import java.util.ArrayList;

public class StreamDemo1 {
    public static void main(String[] args) {
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("张无忌");
        list1.add("周芷若");
        list1.add("赵敏");
        list1.add("张强");
        list1.add("张三丰");

        ArrayList<String> list2 = new ArrayList<>();
        for (String name : list1) {
            if (name.startsWith("张")){
                list2.add(name);
            }
        }

        ArrayList<String> list3 = new ArrayList<>();
        for (String name : list2) {
            if (name.length() == 3){
                list3.add(name);
            }
        }

        for (String name : list3) {
            System.out.println(name);
        }


        System.out.println("=====================================");


        list1.stream().filter(name -> name.startsWith("张")).filter(name -> name.length() == 3).forEach(name -> System.out.println(name));
    }
}
