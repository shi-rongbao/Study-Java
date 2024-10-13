package com.atguigu.fallback;

import com.atguigu.AccountFeignClient;
import com.atguigu.result.RetVal;
import com.atguigu.vo.AccountLockVo;
import org.springframework.stereotype.Component;

/**
 * @author ShiRongbao
 * @create 2024/7/28 17:03
 * @description:
 */
@Component
public class AccountFallBack implements AccountFeignClient {
    @Override
    public RetVal<Object> checkAndLock(AccountLockVo accountLockVo) {
        return RetVal.fail().message("对不起，系统正忙！");
    }
}
