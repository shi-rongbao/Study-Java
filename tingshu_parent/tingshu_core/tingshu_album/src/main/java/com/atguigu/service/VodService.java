package com.atguigu.service;

import com.atguigu.entity.TrackInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author ShiRongbao
 * @create 2024/5/18 9:52
 * @description:
 */
public interface VodService {

    /**
     * 根据传入的文件上传声音
     * @param file 传入的文件
     */
    Map<String, String> uploadTrack(MultipartFile file);

    /**
     * 获取声音媒体信息
     * @param trackInfo 声音信息
     */
    void getTrackMediaInfo(TrackInfo trackInfo);

    void removeTrack(String mediaFileId);
}
