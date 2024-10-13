package com.atguigu;

import com.atguigu.fallback.AccountFallBack;
import com.atguigu.result.RetVal;
import com.atguigu.vo.AccountLockVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author ShiRongbao
 * @create 2024/7/28 17:02
 * @description:
 */
@FeignClient(value = "tingshu-account", fallback = AccountFallBack.class)
public interface AccountFeignClient {

    @PostMapping("/api/account/userAccount/checkAndLock")
    RetVal<Object> checkAndLock(@RequestBody AccountLockVo accountLockVo);

}
