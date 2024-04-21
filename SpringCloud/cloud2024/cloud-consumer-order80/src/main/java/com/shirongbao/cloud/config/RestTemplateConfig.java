package com.shirongbao.cloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author ShiRongbao
 * @create 2024/3/11 17:44
 * @description:
 */
@Configuration
public class RestTemplateConfig {
    @Bean
    @LoadBalanced  // 开启支持负载均衡
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
