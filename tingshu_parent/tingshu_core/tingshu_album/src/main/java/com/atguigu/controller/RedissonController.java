package com.atguigu.controller;

import com.atguigu.util.SleepUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import org.redisson.api.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author ShiRongbao
 * @create 2024/5/24 8:20
 * @description:
 */
@Tag(name = "redisson接口")
@RestController
@RequestMapping("/api/album")
public class RedissonController {

    @Resource
    private RedissonClient redissonClient;

    @GetMapping("/lock")
    public String lock() {
        RLock lock = redissonClient.getLock("lock");
        String uuid = UUID.randomUUID().toString();
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "执行业务" + uuid);
        } finally {
            lock.unlock();
        }
        return Thread.currentThread().getName() + "执行业务" + uuid;
    }

    // 信号量
    @SneakyThrows
    @GetMapping("/park")
    public String park() {
        // 获得信号量
        RSemaphore parkStation = redissonClient.getSemaphore("park_station");
        // 信号量-1
        parkStation.acquire(1);
        return Thread.currentThread().getName() + "找到停车位";
    }

    @SneakyThrows
    @GetMapping("/left")
    public String left() {
        // 获得信号量
        RSemaphore parkStation = redissonClient.getSemaphore("park_station");
        // 信号量-1
        parkStation.release(1);
        return Thread.currentThread().getName() + "离开停车位";
    }


    String uuid = "";

    // 读写锁 —— 读锁
    @GetMapping("/read")
    public String read() {
        RReadWriteLock rwLock = redissonClient.getReadWriteLock("rwLock");
        RLock readLock = rwLock.readLock();
        try {
            readLock.lock();
            return uuid;
        } finally {
            readLock.unlock();
        }
    }

    // 读写锁 —— 写锁
    @GetMapping("/write")
    public void write() {
        RReadWriteLock rwLock = redissonClient.getReadWriteLock("rwLock");
        RLock writeLock = rwLock.writeLock();
        try {
            writeLock.lock();
            SleepUtils.sleep(3);
            uuid = UUID.randomUUID().toString();
        } finally {
            writeLock.unlock();
        }
    }

    // 闭锁
    @GetMapping("/leave")
    public String leave() {
        // 获得RCountDownLatch对象
        RCountDownLatch countDownLatch = redissonClient.getCountDownLatch("close");
        // 离开一个
        countDownLatch.countDown();
        return Thread.currentThread().getName() + "离开";
    }

    @SneakyThrows
    @GetMapping("/closeLock")
    public String closeLock() {
        RCountDownLatch countDownLatch = redissonClient.getCountDownLatch("close");
        // 等5人全部离开才会离开
        countDownLatch.trySetCount(5);
        // 等待
        countDownLatch.await();
        return Thread.currentThread().getName() + "闭锁";
    }

    // 公平锁
    @SneakyThrows
    @GetMapping("/fairLock/{id}")
    public String fairLock(@PathVariable Long id) {
        // 获得公平锁
        RLock fairLock = redissonClient.getFairLock("fairLock");
        try {
            // 加锁
            fairLock.lock();
            // 休眠3秒
            SleepUtils.sleep(3);
            // 解锁
            System.out.println("公平锁" + id);
        } finally {
            fairLock.unlock();
        }
        return "公平锁success" + id;
    }

    // 非公平锁
    @SneakyThrows
    @GetMapping("/unFairLock/{id}")
    public String unFairLock(@PathVariable Long id) {
        // 获得公平锁
        RLock unFairLock = redissonClient.getLock("unFairLock");
        try {
            // 加锁
            unFairLock.lock();
            // 休眠3秒
            SleepUtils.sleep(3);
            // 解锁
            System.out.println("非公平锁" + id);
        } finally {
            unFairLock.unlock();
        }
        return "非公平锁success" + id;
    }
}
