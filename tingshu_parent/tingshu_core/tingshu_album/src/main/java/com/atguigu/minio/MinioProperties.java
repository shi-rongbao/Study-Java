package com.atguigu.minio;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author ShiRongbao
 * @create 2024/5/14 8:10
 * @description:
 */
@ConfigurationProperties(prefix = "minio")
@Data
@Component
public class MinioProperties {
    private String endpoint;
    private String accessKey;
    private String secretKey;
    private String bucketName;
}
