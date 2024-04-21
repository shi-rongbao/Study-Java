package com.study.test3;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ServerTest3 {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(10000);

        //创建线程池对象
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                3,//核心线程数
                21,//最大线程数
                60,//线程保留时间,值
                TimeUnit.SECONDS,//线程保留时间,单位
                new ArrayBlockingQueue<>(3),//阻塞队列长度
                Executors.defaultThreadFactory(),//线程工厂创建线程对象
                new ThreadPoolExecutor.AbortPolicy()//任务拒绝策略
        );
        //等待链接
        while (true) {
            Socket socket = ss.accept();

            pool.submit(new MyRunnable(socket));
        }
    }
}
