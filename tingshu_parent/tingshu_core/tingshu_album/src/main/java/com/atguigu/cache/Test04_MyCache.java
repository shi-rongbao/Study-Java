package com.atguigu.cache;

import com.atguigu.util.SleepUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author ShiRongbao
 * @create 2024/6/11 18:21
 * @description:
 */
public class Test04_MyCache {
    private final Map<String, Object> map = new HashMap<>();

    // 读写锁
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();


    public Object getData(String key) {
        Object value = null;
        try {
            // 上读锁
            rwLock.readLock().lock();
            value = map.get(key);
            // 释放读锁
            rwLock.readLock().unlock();
            if (value == null) {
                try {
                    // 写之前上写锁
                    rwLock.writeLock().lock();
                    value = "shirongbao";
                    map.put(key, value);
                } finally {
                    // 写完后释放写锁
                    rwLock.writeLock().unlock();
                }
                // 为了与finally中释放读锁对应,这里加上读锁
                rwLock.readLock().lock();
            }
        } finally {
            // 最后释放读锁
            rwLock.readLock().unlock();
        }
        return value;
    }

    /*public Object getData(String key) {
        Object value = map.get(key);
        if (value == null) {
            value = "shirongbao";
            map.put(key, value);
        }
        return value;
    }*/

    public static void main(String[] args) {
        Test04_MyCache myCache = new Test04_MyCache();
        Object value1 = myCache.getData("你好");
        System.out.println(value1);
        Object value2 = myCache.getData("你好");
        System.out.println(value2);
    }
}
