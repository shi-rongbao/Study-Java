package com.atguigu;

import com.atguigu.threadpool.MyThreadPool;
import com.atguigu.threadpool.MyThreadProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class AlbumApplication {
   public static void main(String[] args) {
      SpringApplication.run(AlbumApplication.class, args);
   }
}