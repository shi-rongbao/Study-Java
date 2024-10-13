package com.atguigu.fallback;

import com.atguigu.SearchFeignClient;
import com.atguigu.result.RetVal;
import org.springframework.stereotype.Component;

/**
 * @author ShiRongbao
 * @create 2024/7/18 18:50
 * @description:
 */
@Component
public class SearchFallBack implements SearchFeignClient {
    @Override
    public RetVal<String> onSaleAlbum(Long albumId) {
        return RetVal.fail("对不起，系统正忙！");
    }

    @Override
    public RetVal<String> offSaleAlbum(Long albumId) {
        return RetVal.fail("对不起，系统正忙！");
    }
}
