package com.shirongbao.redis;

import com.shirongbao.redis.pojo.User;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
class RedisDemoApplicationTests {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void testString() {
        // 获取操作String类型的对象
        ValueOperations<String, Object> redis = redisTemplate.opsForValue();
        // 插入数据
        redis.set("spring:name", "帅逼");
        // 获取数据
        String name = (String) redis.get("spring:name");
        System.out.println("name: " + name);
    }

    @Test
    void testSaveUser(){
        // 获取操作String类型的对象
        ValueOperations<String, Object> redis = redisTemplate.opsForValue();
        // 插入数据
        User user = new User("张三", 23);
        redis.set("spring:user1", user);
        // 获取数据
        User zhangSan = (User) redis.get("spring:user1");
        System.out.println(zhangSan);
    }

}
