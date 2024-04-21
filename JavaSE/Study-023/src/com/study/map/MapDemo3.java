package com.study.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapDemo3 {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("破败之王", "佛耶戈");
        map.put("探险家", "伊泽瑞尔");
        map.put("暗夜猎手", "薇恩");
        map.put("诺克萨斯之手", "德莱厄斯");
        Set<Map.Entry<String, String>> set = map.entrySet();

        //lambda表达式遍历
        set.forEach(entry -> System.out.println(entry.getKey() + " = " + entry.getValue()));

        System.out.println("==========================");

        //迭代器遍历
        Iterator<Map.Entry<String, String>> it = set.iterator();
        while (it.hasNext()) {

            Map.Entry<String, String> entry = it.next();
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
        System.out.println("====================");

        //增强for遍历
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }
}
