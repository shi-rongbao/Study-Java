package com.study.map;

import java.util.HashMap;
import java.util.Map;

public class MapDemo1 {
    public static void main(String[] args) {
        //Map是接口,只能创建Map的实现类对象
        Map<String, String> map = new HashMap<>();
        map.put("鸣人","雏田");
        map.put("佐助","小樱");
        String str = map.put("柱间", "斑");
        System.out.println(str);

        System.out.println(map);

        String remove = map.remove("柱间");
        System.out.println(remove);

        System.out.println(map);

        // map.clear();

        boolean result1 = map.containsKey("鸣人");
        System.out.println(result1);
        boolean result2 = map.containsValue("雏田");
        System.out.println(result2);

        System.out.println(map);

        boolean result = map.isEmpty();
        System.out.println(result);

        //map.clear();
        boolean empty = map.isEmpty();
        System.out.println(empty);

        int size = map.size();
        System.out.println(size);

        //System.out.println(map);
    }
}
