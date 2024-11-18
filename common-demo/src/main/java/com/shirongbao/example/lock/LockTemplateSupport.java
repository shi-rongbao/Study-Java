package com.shirongbao.example.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

/**
 * @author ShiRongbao
 * @create 2024/11/18
 * @description: 加锁模板类
 */
public class LockTemplateSupport {

    private final ReentrantLock lock = new ReentrantLock();

    public void executeWithLock (Long timeout, TimeUnit unit, Runnable task) throws InterruptedException {
        // 尝试获取锁
        boolean locked = lock.tryLock(timeout, unit);

        if (!locked) {
            return ;
        }

        // 获取锁成功，执行任务
        try {
            task.run();
        } finally {
            // 最终释放锁
            lock.unlock();
        }

    }

    public <T> T executeWithLock (Long timeout, TimeUnit unit, Supplier<T> task) throws InterruptedException {
        // 尝试获取锁
        boolean locked = lock.tryLock(timeout, unit);

        if (!locked) {
            // or throw exception
            return null;
        }

        // 获取锁成功，执行任务
        try {
            return task.get();
        } finally {
            // 最终释放锁
            lock.unlock();
        }

    }

}
