package com.shirongbao.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ShiRongbao
 * @create 2024/10/9
 * @description:
 */
public class ThreadDemo3 {
    public static void main(String[] args) {
        // 使用HashMap
        // Map<String, String> map = new HashMap<>();
        // 使用ConcurrentHashMap
        Map<String, String> map = new ConcurrentHashMap<>();


        for (int i = 0; i < 350; i++) {
            new Thread(() -> {
                // 写
                String uuid = UUID.randomUUID().toString();
                map.put(uuid.substring(0,2), uuid.substring(2, 5));

                // 读
                System.out.println(map);
            }, "Thread1").start();
        }
    }
}
