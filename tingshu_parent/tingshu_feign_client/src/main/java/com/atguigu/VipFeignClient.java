package com.atguigu;

import com.atguigu.entity.VipServiceConfig;
import com.atguigu.fallback.VipFallBack;
import com.atguigu.result.RetVal;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * @author ShiRongbao
 * @create 2024/7/27 12:04
 * @description:
 */
@FeignClient(value = "tingshu-user", fallback = VipFallBack.class)
public interface VipFeignClient {

    @GetMapping("/api/user/vipConfig/getVipServiceConfig/{vipConfigId}")
    RetVal<VipServiceConfig> getVipServiceConfigById(@PathVariable Long vipConfigId);
}
