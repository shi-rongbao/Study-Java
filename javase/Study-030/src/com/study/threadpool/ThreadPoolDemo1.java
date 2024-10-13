package com.study.threadpool;

import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo1 {
    public static void main(String[] args) {
        //创建线程池对象
        ExecutorService pool1 = Executors.newFixedThreadPool(3);

        //给线程池提交任务
        pool1.submit(new MyRunnable());
        pool1.submit(new MyRunnable());
        pool1.submit(new MyRunnable());
        pool1.submit(new MyRunnable());
        pool1.submit(new MyRunnable());

        //摧毁线程池,同时也将摧毁线程池中的所有线程
        pool1.shutdown();
    }
}
