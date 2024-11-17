package com.shirongbao.todoapp.config;

import com.shirongbao.todoapp.model.Todo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author ShiRongbao
 * @create 2024/11/17
 * @description:
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Todo> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Todo> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        // 使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
        Jackson2JsonRedisSerializer<Todo> serializer = new Jackson2JsonRedisSerializer<>(Todo.class);

        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(serializer);
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(serializer);
        template.afterPropertiesSet();

        return template;
    }

}
