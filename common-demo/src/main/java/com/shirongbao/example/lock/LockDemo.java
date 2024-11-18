package com.shirongbao.example.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ShiRongbao
 * @create 2024/11/18
 * @description:
 */
public class LockDemo {

    private final ReentrantLock lock = new ReentrantLock();

    private final LockTemplateSupport lockTemplateSupport = new LockTemplateSupport();

    public void doSomething2 () throws InterruptedException {
        // 执行方法，同时也是加锁的
        lockTemplateSupport.executeWithLock(1L, TimeUnit.SECONDS, this::coreMethod);
    }

    public void doSomething1 () throws InterruptedException {
        // 加锁
        boolean locked = lock.tryLock(1, TimeUnit.SECONDS);
        if (!locked) {
            return ;
        }

        // 加锁成功
        try {
            coreMethod();
        } finally {
            lock.unlock();
        }
    }


    public void coreMethod () {
        // do something
        System.out.println("do something");
    }
}
