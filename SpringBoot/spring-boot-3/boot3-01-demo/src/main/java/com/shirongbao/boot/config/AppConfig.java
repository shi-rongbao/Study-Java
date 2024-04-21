package com.shirongbao.boot.config;

import com.shirongbao.boot.bean.Pig;
import com.shirongbao.boot.bean.Sheep;
import com.shirongbao.boot.bean.User;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * @author ShiRongbao
 * @create 2024/2/9 11:22
 * @description:
 */
// @Import("com.alibaba.druid.FastsqlException")

/**
 * 1.將类value中标识的类开启属性绑定
 * 2.默认将当前类放入springIoC容器中
 */
@EnableConfigurationProperties(Sheep.class)  // 该注解一般用于导入第三方写好的组件进行属性绑定
@SpringBootConfiguration
public class AppConfig {

    @Bean
    @ConfigurationProperties(prefix = "pig")
    public Pig pig(){
        /*return new Pig(null, "乔治", 3);*/ // 不起作用
        return new Pig();
    }

    @Scope("prototype")
    @Bean
    public User user(){
        return new User();
    }

}
