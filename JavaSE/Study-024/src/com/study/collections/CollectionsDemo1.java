package com.study.collections;

import java.util.ArrayList;
import java.util.Collections;

public class CollectionsDemo1 {
    public static void main(String[] args) {
        ArrayList<String> list1 = new ArrayList<>();

        Collections.addAll(list1, "fuck", "your", "ass", "fuck!");
        System.out.println(list1);
        list1.forEach(s -> System.out.println(s));

        Collections.shuffle(list1);

        System.out.println(list1);

        Collections.sort(list1);
        System.out.println(list1);

        System.out.println("===================");

        ArrayList<Integer> list2 = new ArrayList<>();
        Collections.addAll(list2, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        System.out.println(list2);
        Collections.sort(list2, ((o1, o2) -> o2 - o1));
        System.out.println(list2);

        System.out.println("===================");
        //需要元素有序
        ArrayList<Integer> list3 = new ArrayList<>();
        Collections.addAll(list3, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        int i = Collections.binarySearch(list3, 3);
        System.out.println(i);

        System.out.println("===================");

        ArrayList<Integer> list4 = new ArrayList<>();
        Collections.addAll(list4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        System.out.println(list4);
        Collections.copy(list4, list3);
        System.out.println(list4);

        System.out.println("===================");

        ArrayList<Integer> list5 = new ArrayList<>();
        Collections.addAll(list5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        System.out.println(list5);
        Collections.fill(list5, 2);
        System.out.println(list5);

        System.out.println("===================");

        Integer max = Collections.max(list3);
        Integer min = Collections.min(list3);
        System.out.println(max);
        System.out.println(min);

        System.out.println("===================");

        ArrayList<Integer> list6 = new ArrayList<>();
        Collections.addAll(list6, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        System.out.println(list6);
        Collections.swap(list6, 2, 3);//i和j是要交换的索引
        System.out.println(list6);
    }
}