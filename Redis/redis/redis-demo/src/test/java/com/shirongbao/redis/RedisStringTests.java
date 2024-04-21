package com.shirongbao.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shirongbao.redis.pojo.User;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Map;

@SpringBootTest
class RedisStringTests {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    // JSON转换对象
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testString() {
        // 获取操作String类型的对象
        ValueOperations<String, String> redis = stringRedisTemplate.opsForValue();
        // 插入数据
        redis.set("spring:string:name", "帅逼");
        // 获取数据
        String name = redis.get("spring:string:name");
        System.out.println("name: " + name);
    }

    @Test
    void testSaveUser() throws JsonProcessingException {
        // 获取操作String类型的对象
        ValueOperations<String, String> redis = stringRedisTemplate.opsForValue();
        // 创建对象
        User user = new User("张三", 23);
        // 将对象转成JSON字符串
        String json = objectMapper.writeValueAsString(user);
        // 插入数据
        redis.set("spring:string:user1", json);
        // 获取数据
        String jsonUser = redis.get("spring:string:user1");
        // 将JSON字符串反序列化成对象
        User zhangSan = objectMapper.readValue(jsonUser, User.class);
        System.out.println(zhangSan);
    }

    @Test
    void testHash(){
        HashOperations<String, Object, Object> redis = stringRedisTemplate.opsForHash();

        // 插入数据
        redis.put("spring:string:hash", "name", "虎哥");
        redis.put("spring:string:hash", "age", "21");

        // 获取数据
        Map<Object, Object> map = redis.entries("spring:string:hash");
        map.forEach((key, value) -> System.out.println("key: " + key + " = " + "value: " + value));
    }

}
