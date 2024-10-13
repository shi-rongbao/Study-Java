package com.shirongbao.jedis.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author ShiRongbao
 * @create 2024/4/18 22:43
 * @description:
 */
public class JedisConnectionFactory {
    private static final JedisPool jedisPool;

    static {
        // 配置连接池
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(8);
        jedisPoolConfig.setMaxIdle(8);
        jedisPoolConfig.setMinIdle(0);
        jedisPoolConfig.setMaxWaitMillis(1000);
        // 创建连接池对象
        jedisPool = new JedisPool(jedisPoolConfig, "8.130.175.86", 6377, 1000, "7447664226");
    }

    public static Jedis getJedis() {
        return jedisPool.getResource();
    }
}
