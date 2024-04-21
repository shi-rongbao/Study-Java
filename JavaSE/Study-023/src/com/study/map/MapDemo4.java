package com.study.map;

import java.util.HashMap;
import java.util.Map;

public class MapDemo4 {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("破败之王", "佛耶戈");
        map.put("探险家", "伊泽瑞尔");
        map.put("暗夜猎手", "薇恩");
        map.put("诺克萨斯之手", "德莱厄斯");

        map.forEach((key, value) -> System.out.println(key + "=" + value));

        map.forEach((key, value) -> System.out.println(key + " = " + value));
    }
}
