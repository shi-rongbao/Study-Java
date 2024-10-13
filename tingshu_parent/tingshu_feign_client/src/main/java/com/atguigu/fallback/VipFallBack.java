package com.atguigu.fallback;

import com.atguigu.VipFeignClient;
import com.atguigu.entity.VipServiceConfig;
import com.atguigu.result.RetVal;
import org.springframework.stereotype.Component;


/**
 * @author ShiRongbao
 * @create 2024/7/27 12:04
 * @description:
 */
@Component
public class VipFallBack implements VipFeignClient {

    @Override
    public RetVal<VipServiceConfig> getVipServiceConfigById(Long vipConfigId) {
        return RetVal.fail(new VipServiceConfig()).message("对不起，系统正忙！");
    }
}
