package com.study.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class MapDemo2 {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("鸣人", "雏田");
        map.put("佐助", "小樱");
        map.put("柱间", "斑");

        Set<String> keys = map.keySet();

        //lambda表达式遍历
        keys.forEach( key -> {
            String value = map.get(key);
            System.out.println(key + " = " + value);
        });

        System.out.println("======================");

        //迭代器遍历
        Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
            String key = it.next();
            String value = map.get(key);
            System.out.println(key + " = " + value);
        }

        System.out.println("======================");

        //增强for遍历
        for (String key : keys) {
            //System.out.println(key);
            String value = map.get(key);
            System.out.println(key + " = " + value);
        }
    }
}
