package com.shirongbao.test;

import com.shirongbao.jedis.util.JedisConnectionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.Map;

/**
 * @author ShiRongbao
 * @create 2024/4/18 22:29
 * @description:
 */
public class JedisTest {
    private Jedis jedis;

    @BeforeEach
    void setUp() {
        // new Jedis对象，指定ip地址与端口号
        // jedis = new Jedis("8.130.175.86", 6377);
        // 从jedis连接池中获取jedis
        jedis = JedisConnectionFactory.getJedis();
        // 设置密码
        jedis.auth("7447664226");
        // 选择默认库
        jedis.select(0);
    }

    @Test
    void testString() {
        // 存入数据
        String result = jedis.set("java:test:name", "fuck");
        System.out.println("result: " + result);
        // 获取数据
        String value = jedis.get("java:test:name");
        System.out.println("value: " + value);
    }

    @Test
    void testHash() {
        // 插入hash数据
        jedis.hsetnx("java:test:student", "name", "张三");
        jedis.hsetnx("java:test:student", "age", "20");
        jedis.hsetnx("java:test:student", "sex", "男");

        // 获取数据
        Map<String, String> student = jedis.hgetAll("java:test:student");
        student.forEach((key, value) -> System.out.println(key + ": " + value));
    }

    @AfterEach
    void tearDown() {
        if (jedis != null) {
            jedis.close();
        }
    }

}
