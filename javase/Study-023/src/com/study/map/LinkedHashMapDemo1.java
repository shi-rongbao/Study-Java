package com.study.map;

import java.util.LinkedHashMap;

public class LinkedHashMapDemo1 {
    public static void main(String[] args) {
        //LInkedHashMap 有序 键唯一
        //底层是数组＋双向链表＋红黑树

        LinkedHashMap<String,Integer> lhm1 = new LinkedHashMap<>();
        lhm1.put("lisi",24);
        lhm1.put("zhaoliu",26);
        lhm1.put("wangwu",25);
        lhm1.put("zhangsan",23);
        System.out.println(lhm1);

        LinkedHashMap<String, String> lhm = new LinkedHashMap<>();
        lhm.put("鸣人", "雏田");
        lhm.put("佐助", "小樱");
        lhm.put("佐井", "井野");
        lhm.put("阿斯玛", "夕日红");

        lhm.forEach((key, value) -> System.out.println(key + "=" + value));
    }
}
