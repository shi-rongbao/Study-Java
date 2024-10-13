package com.atguigu.threadpool;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ShiRongbao
 * @create 2024/7/22 12:18
 * @description:
 */
@Data
@ConfigurationProperties(prefix = "thread.pool")
public class MyThreadProperties {
    public Integer corePoolSize = 16;
    public Integer maximumPoolSize = 32;
    public Integer keepAliveTime = 50;
    public Integer queueLength = 100;
}
