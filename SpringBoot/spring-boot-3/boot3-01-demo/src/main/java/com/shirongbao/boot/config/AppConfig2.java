package com.shirongbao.boot.config;

import com.shirongbao.boot.bean.Cat;
import com.shirongbao.boot.bean.Dog;
import com.shirongbao.boot.bean.User;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;

/**
 * @author ShiRongbao
 * @create 2024/2/9 11:38
 * @description:
 */
@SpringBootConfiguration
public class AppConfig2 {
    @ConditionalOnClass(name = "com.alibaba.druid.FastsqlException")
    @Bean
    public Cat cat01() {
        return new Cat();
    }

    @ConditionalOnMissingClass("com.alibaba.druid.FastsqlException")
    @Bean
    public Dog dog01() {
        return new Dog();
    }

    @ConditionalOnBean(Cat.class)
    @Bean
    public User zhangSan(){
        return new User(23, "zhangSan");
    }

    @ConditionalOnMissingBean(Cat.class)
    @Bean
    public User liSi(){
        return new User(24, "LiSi");
    }
}
