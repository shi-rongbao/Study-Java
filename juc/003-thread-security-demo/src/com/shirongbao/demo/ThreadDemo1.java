package com.shirongbao.demo;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author ShiRongbao
 * @create 2024/10/9
 * @description:
 * 演示list集合线程不安全问题
 * ArrayList是线程不安全的，在add方法没有添加synchronized
 * List的实现类Vector是线程安全的，因为他每一个方法都使用synchronized修饰了
 */
public class ThreadDemo1 {

    public static void main(String[] args) {
        // 使用ArrayList
        // List<String> list = new ArrayList<>();
        // 使用Vector
        // List<String> list = new Vector<>();
        // 使用Collections
        // List<String> list = Collections.synchronizedList(new ArrayList<>());
        // 写时复制技术，在读的时候可以并发读不会有问题，如果要写，先复制一份，然后独立写，写完后重新赋值新的内容
        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                // 向集合中添加数据
                list.add(UUID.randomUUID().toString());

                // 获取集合中的数据
                System.out.println(list);
            }, String.valueOf(i)).start();
        }

    }

}
