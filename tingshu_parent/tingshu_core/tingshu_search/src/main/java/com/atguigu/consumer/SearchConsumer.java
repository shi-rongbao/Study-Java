package com.atguigu.consumer;

import com.atguigu.constant.KafkaConstant;
import com.atguigu.service.SearchService;
import jakarta.annotation.Resource;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author ShiRongbao
 * @create 2024/7/18 19:15
 * @description:
 */
@Component
public class SearchConsumer {

    @Resource
    private SearchService searchService;

    /**
     * 使用kafka监听专辑上架信息
     * @param albumId albumId
     */
    @KafkaListener(topics = KafkaConstant.ONSALE_ALBUM_QUEUE)
    public void onSaleAlbum(Long albumId) {
        if (albumId != null) {
            searchService.onSaleAlbum(albumId);
        }
    }

    /**
     * 使用kafka监听专辑下架信息
     * @param albumId albumId
     */
    @KafkaListener(topics = KafkaConstant.OFFSALE_ALBUM_QUEUE)
    public void offSaleAlbum(Long albumId) {
        if (albumId != null) {
            searchService.offSaleAlbum(albumId);
        }
    }

}
