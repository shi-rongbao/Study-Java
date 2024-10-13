package com.study.stream;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StreamDemo3 {
    public static void main(String[] args) {
        HashMap<String,Integer> hm = new HashMap<>();
        hm.put("fuck",1);
        hm.put("my",2);
        hm.put("roommate",3);

        Set<Map.Entry<String, Integer>> entries = hm.entrySet();
        entries.stream().forEach(entry -> System.out.println(entry.getKey() + " = " + entry.getValue()));
    }
}
