package com.atguigu.threadpool;

import jakarta.annotation.Resource;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ShiRongbao
 * @create 2024/7/22 12:03
 * @description:
 */
@Configuration
@EnableConfigurationProperties(MyThreadProperties.class)
public class MyThreadPool {

    @Resource
    private MyThreadProperties myThreadProperties;

    @Bean
    public ThreadPoolExecutor myPoolExecutor() {
        return new ThreadPoolExecutor(myThreadProperties.getCorePoolSize(),
                myThreadProperties.getMaximumPoolSize(),
                myThreadProperties.getKeepAliveTime(),
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(myThreadProperties.getQueueLength()),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

}
