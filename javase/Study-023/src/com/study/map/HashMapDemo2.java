package com.study.map;

import java.util.*;

public class HashMapDemo2 {
    public static void main(String[] args) {
        String[] arrAttractions = {"A", "B", "C", "D"};//创建四个景点所对应的数组
        ArrayList<String> list = new ArrayList<>();//创建景点票数集合
        Random r = new Random();//使用random随机数
        for (int i = 0; i < 80; i++) {//80次循环
            int index = r.nextInt(arrAttractions.length);//获取区间为数组长度的随机数索引
            list.add(arrAttractions[index]);//记录一次投票
        }

        HashMap<String, Integer> hm = new HashMap<>();//创建集合hm
        for (String attractions : list) {//增强for遍历list集合
            if (hm.containsKey(attractions)) {//如果hm中有对应景点名称的key
                int value = hm.get(attractions);//获取现在key所对应的value
                value++;//value自增一次
                hm.put(attractions, value);//覆盖原有的value值
            } else {//如果第一次投票到景点
                hm.put(attractions, 1);//将信息添加到hm集合中,value为1
            }
        }

        System.out.println(hm);

        hm.forEach((key, value) -> System.out.println(key + " = " + value));//遍历集合

        int max = 0;

        Set<Map.Entry<String, Integer>> entries = hm.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            int value = entry.getValue();
            if (value > max) {
                max = value;
            }
        }
        System.out.println(max);

        for (Map.Entry<String, Integer> entry : entries) {
            Integer value = entry.getValue();
            if (max == value) {
                System.out.println("次数最多的是:" + entry.getKey());
            }
        }
    }
}
