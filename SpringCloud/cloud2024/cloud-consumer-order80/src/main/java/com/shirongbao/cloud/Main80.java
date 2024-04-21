package com.shirongbao.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ShiRongbao
 * @create 2024/3/11 17:13
 * @description:
 */
@SpringBootApplication
@EnableDiscoveryClient  // 使用consul进行服务注册
public class Main80 {
    public static void main(String[] args) {
        SpringApplication.run(Main80.class, args);
    }
}
