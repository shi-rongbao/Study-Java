package com.shirongbao.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ShiRongbao
 * @create 2024/3/19 12:34
 * @description:
 */
@EnableDiscoveryClient
@SpringBootApplication
public class Main9002 {
    public static void main(String[] args) {
        SpringApplication.run(Main9002.class, args);
    }
}
