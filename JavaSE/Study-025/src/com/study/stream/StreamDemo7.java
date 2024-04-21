package com.study.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Function;

public class StreamDemo7 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "张无忌-15", "周芷若-14", "赵敏-13", "张强-20");

        list.stream().map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                String[] arr = s.split("-");
                String ageString = arr[1];
                int age = Integer.parseInt(ageString);
                return age;
            }
        }).forEach(age -> System.out.println(age));

        list.stream().map(s -> Integer.parseInt(s.split("-")[1])).forEach(age -> System.out.println(age));
    }
}
