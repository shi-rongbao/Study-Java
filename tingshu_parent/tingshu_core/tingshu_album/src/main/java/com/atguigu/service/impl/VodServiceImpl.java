package com.atguigu.service.impl;

import com.atguigu.config.VodProperties;
import com.atguigu.entity.TrackInfo;
import com.atguigu.service.VodService;
import com.atguigu.util.UploadFileUtil;
import com.qcloud.vod.VodUploadClient;
import com.qcloud.vod.model.VodUploadRequest;
import com.qcloud.vod.model.VodUploadResponse;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.vod.v20180717.VodClient;
import com.tencentcloudapi.vod.v20180717.models.DeleteMediaRequest;
import com.tencentcloudapi.vod.v20180717.models.DescribeMediaInfosRequest;
import com.tencentcloudapi.vod.v20180717.models.DescribeMediaInfosResponse;
import com.tencentcloudapi.vod.v20180717.models.MediaInfo;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ShiRongbao
 * @create 2024/5/18 9:52
 * @description:
 */
@Slf4j
@Service
public class VodServiceImpl implements VodService {

    @Resource
    private VodProperties vodProperties;

    @SneakyThrows
    @Override
    public Map<String, String> uploadTrack(MultipartFile file) {
        // 声音上传的临时文件
        String tempPath = UploadFileUtil.uploadTempPath(vodProperties.getTempPath(), file);
        VodUploadClient client = new VodUploadClient(vodProperties.getSecretId(), vodProperties.getSecretKey());
        VodUploadRequest request = new VodUploadRequest();
        request.setMediaFilePath(tempPath);
        VodUploadResponse response = client.upload(vodProperties.getRegion(), request);
        Map<String, String> map = new HashMap<>();
        map.put("mediaFileId", response.getFileId());
        map.put("mediaUrl", response.getMediaUrl());
        return map;
    }

    @SneakyThrows
    @Override
    public void getTrackMediaInfo(TrackInfo trackInfo) {
        // 使用 vodProperties 中的 secret ID 和 secret key 创建 Credential 对象
        Credential cred = new Credential(vodProperties.getSecretId(), vodProperties.getSecretKey());
        // 使用凭证和 vodProperties 中的区域初始化 VodClient 对象
        VodClient client = new VodClient(cred, vodProperties.getRegion());
        // 创建描述媒体信息的请求对象
        DescribeMediaInfosRequest req = new DescribeMediaInfosRequest();
        // 在请求对象中设置媒体文件 ID
        String[] mediaFileIds = {trackInfo.getMediaFileId()};
        req.setFileIds(mediaFileIds);
        // 向 VOD 客户端发送请求并接收响应
        DescribeMediaInfosResponse resp = client.DescribeMediaInfos(req);
        // 检查响应中是否包含任何媒体信息
        if (resp.getMediaInfoSet().length > 0) {
            // 从响应中获取第一个媒体信息对象
            MediaInfo mediaInfo = resp.getMediaInfoSet()[0];
            // 使用媒体元数据中的大小设置 trackInfo 中的媒体大小
            trackInfo.setMediaSize(mediaInfo.getMetaData().getSize());
            // 使用媒体元数据中的时长（转换为 BigDecimal）设置 trackInfo 中的媒体时长
            trackInfo.setMediaDuration(BigDecimal.valueOf(mediaInfo.getMetaData().getDuration()));
            // 使用媒体基本信息中的类型设置 trackInfo 中的媒体类型
            trackInfo.setMediaType(mediaInfo.getBasicInfo().getType());
        }
    }

    @SneakyThrows
    @Override
    public void removeTrack(String mediaFileId) {
        // 实例化一个认证对象，入参需要传入腾讯云账户 SecretId 和 SecretKey，此处还需注意密钥对的保密
        // 代码泄露可能会导致 SecretId 和 SecretKey 泄露，并威胁账号下所有资源的安全性。以下代码示例仅供参考，建议采用更安全的方式来使用密钥，请参见：https://cloud.tencent.com/document/product/1278/85305
        // 密钥可前往官网控制台 https://console.cloud.tencent.com/cam/capi 进行获取
        Credential cred = new Credential(vodProperties.getSecretId(), vodProperties.getSecretKey());
        // 实例化要请求产品的client对象,clientProfile是可选的
        VodClient client = new VodClient(cred, vodProperties.getRegion());
        // 实例化一个请求对象,每个接口都会对应一个request对象
        DeleteMediaRequest req = new DeleteMediaRequest();
        req.setFileId(mediaFileId);
        // 返回的resp是一个DeleteMediaResponse的实例，与请求对象对应
        client.DeleteMedia(req);
    }
}
