package com.atguigu.minio;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * @author ShiRongbao
 * @create 2024/5/13 21:36
 * @description:
 */
@Slf4j
@Component
@EnableConfigurationProperties({MinioProperties.class})
public class MinioUploader {
    @Resource
    private MinioProperties minioProperties;
    @Resource
    private MinioClient minioClient;

    @SneakyThrows
    @Bean
    public MinioClient minioClient() {
        // Create a minioClient with the MinIO server playground, its access key and secret key.
        MinioClient minioClient = MinioClient.builder()
                .endpoint(minioProperties.getEndpoint())
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .build();
        // 创建一个bucket如果不存在的话
        boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(minioProperties.getBucketName()).build());
        if (!found) {
            // 不存在就创建一个bucket
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(minioProperties.getBucketName()).build());
        } else {
            // 存在就输出
            log.warn("Bucket '{}' already exists.", minioProperties.getBucketName());
        }
        return minioClient;
    }

    public String uploadFile(MultipartFile file) throws Exception {
        // 设置文件名
        String prefix = UUID.randomUUID().toString().replaceAll("-", "");
        String suffix = FilenameUtils.getExtension(file.getOriginalFilename());
        String fileName = prefix + "." + suffix;

        // 上传文件
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(minioProperties.getBucketName())
                        .object(fileName)
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .contentType(file.getContentType())
                        .build());
        log.info("file is successfully uploaded as object {} to bucket {}", fileName, minioProperties.getBucketName());

        // 将存储后的文件访问地址返回
        return minioProperties.getEndpoint() + "/" + minioProperties.getBucketName() + "/" + fileName;
    }

}
