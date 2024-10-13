package com.atguigu;

import com.atguigu.fallback.SearchFallBack;
import com.atguigu.result.RetVal;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author ShiRongbao
 * @create 2024/7/18 18:50
 * @description:
 */
@FeignClient(value = "tingshu-search", fallback = SearchFallBack.class)
public interface SearchFeignClient {

    @GetMapping("/api/search/albumInfo/onSaleAlbum/{albumId}")
    RetVal<String> onSaleAlbum(@PathVariable Long albumId);

    @GetMapping("/api/search/albumInfo/offSaleAlbum/{albumId}")
    RetVal<String> offSaleAlbum(@PathVariable Long albumId);

}
