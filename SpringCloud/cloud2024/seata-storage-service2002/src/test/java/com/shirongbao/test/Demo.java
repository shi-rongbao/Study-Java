package com.shirongbao.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author ShiRongbao
 * @create 2024/3/25 15:45
 * @description:
 */
@SpringBootTest
@SpringBootConfiguration
public class Demo extends Object{
    @Test
    void testHashMap() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "第一个");
        map.put(2, "第二个");
        map.put(3, "第三个");
        map.put(4, "第四个");

        Set<Map.Entry<Integer, String>> entries = map.entrySet();
        for (Map.Entry<Integer, String> entry : entries) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }

        System.out.println("===========================");

        map.forEach((key, value) -> System.out.println(key + " = " + value));

        System.out.println("===========================");
    }
}
