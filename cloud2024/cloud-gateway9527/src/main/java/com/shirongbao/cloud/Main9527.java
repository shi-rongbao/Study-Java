package com.shirongbao.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ShiRongbao
 * @create 2024/3/16 22:54
 * @description:
 */
@SpringBootApplication
@EnableDiscoveryClient  // 开启consul服务注册与发现
public class Main9527 {
    public static void main(String[] args) {
        SpringApplication.run(Main9527.class,args);
    }
}
