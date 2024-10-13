package com.shirongbao.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author ShiRongbao
 * @create 2024/3/13 10:59
 * @description:
 */
@SpringBootApplication
@MapperScan("com.shirongbao.cloud.mapper")
@EnableDiscoveryClient  // 使用consul进行服务注册
@RefreshScope  // 开启动态刷新，consul服务器的data变更后，数据动态刷新
public class Main8002 {
    public static void main(String[] args) {
        SpringApplication.run(Main8002.class, args);
    }
}
