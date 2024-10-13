package com.atguigu.controller;

import com.atguigu.minio.MinioUploader;
import com.atguigu.result.RetVal;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ShiRongbao
 * @create 2024/5/14 8:32
 * @description:
 */
@Tag(name = "上传管理接口")
@RestController
@RequestMapping(value = "/api/album")
public class FileUploadController {

    @Resource
    private MinioUploader minioUploader;

    @Operation(summary = "文件上传")
    @PostMapping("/fileUpload")
    public RetVal fileUpload(MultipartFile file) throws Exception {
        String fileUrl = minioUploader.uploadFile(file);
        return RetVal.ok(fileUrl);
    }
}
