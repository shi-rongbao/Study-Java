package com.shirongbao.boot3.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ShiRongbao
 * @create 2024/3/2 15:15
 * @description:
 */
@RestController
public class RedisTestController {

    @Autowired
    StringRedisTemplate redisTemplate;

    @GetMapping("/count")
    public String count() {
        Long hello = redisTemplate.opsForValue().increment("hello");
        return "访问了【" + hello + "】次";
    }
}
