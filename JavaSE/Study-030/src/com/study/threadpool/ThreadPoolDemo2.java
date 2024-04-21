package com.study.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo2 {
    public static void main(String[] args) {
        /*
            自定义线程池使用ThreadPoolExecutor 类的构造方法
         */

        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                3,//第一个参数,是核心线程的数量
                6,//第二个参数,是最大线程的数量,临时线程数量等于最大线程数量-核心线程数量
                60,//第三个参数,是设置空闲线程最大存活时间的值
                TimeUnit.SECONDS,//第四个参数,是设置空闲线程池最大存活时间的单位
                new ArrayBlockingQueue<>(3),//第五个参数,是设置任务队列的长度,Array可以设置队伍的长度,Linked不能设置队伍的长度
                Executors.defaultThreadFactory(),//第六个参数,是创建线程工厂,用于线程池创建线程
                new ThreadPoolExecutor.AbortPolicy()//第七个参数,是设置任务的拒绝策略
        );

        //以上是自定义线程池的方法

    }
}
