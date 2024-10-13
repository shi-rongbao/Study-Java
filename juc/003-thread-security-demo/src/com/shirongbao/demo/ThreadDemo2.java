package com.shirongbao.demo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author ShiRongbao
 * @create 2024/10/9
 * @description: 演示HashSet线程不安全
 */
public class ThreadDemo2 {

    public static void main(String[] args) {

        // 使用HashSet
        // Set<String> set = new HashSet<>();
        // 使用CopyOnWriteArraySet()
        Set<String> set = new CopyOnWriteArraySet<>();

        for (int i = 0; i < 150; i++) {
            new Thread(() -> {
                // 写
                set.add(UUID.randomUUID().toString().substring(0, 3));

                // 读
                System.out.println(set);
            }, "Thread1").start();
        }

    }

}
