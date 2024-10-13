package com.atguigu.controller;

import com.atguigu.entity.VipServiceConfig;
import com.atguigu.result.RetVal;
import com.atguigu.service.VipServiceConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ShiRongbao
 * @create 2024/7/22 17:49
 * @description:
 */
@Tag(name = "VIP服务接口")
@RestController
@RequestMapping("/api/user/vipConfig")
public class VipController {

    @Resource
    private VipServiceConfigService vipServiceConfigService;

    @Operation(summary = "查询所有VIP配置")
    @GetMapping("/findAllVipConfig")
    public RetVal<List<VipServiceConfig>> findAllVipConfig() {
        List<VipServiceConfig> list = vipServiceConfigService.list();
        return RetVal.ok(list);
    }

    @Operation(summary = "获取VIP配置信息")
    @GetMapping("/getVipServiceConfig/{vipConfigId}")
    public RetVal<VipServiceConfig> getVipServiceConfigById(@PathVariable Long vipConfigId) {
        VipServiceConfig vipServiceConfig = vipServiceConfigService.getById(vipConfigId);
        return RetVal.ok(vipServiceConfig);
    }

}
