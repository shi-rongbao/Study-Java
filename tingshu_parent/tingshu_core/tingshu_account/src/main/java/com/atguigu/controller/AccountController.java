package com.atguigu.controller;

import com.atguigu.entity.UserAccount;
import com.atguigu.login.TingShuLogin;
import com.atguigu.result.RetVal;
import com.atguigu.service.UserAccountService;
import com.atguigu.util.AuthContextHolder;
import com.atguigu.vo.AccountLockVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * @author ShiRongbao
 * @create 2024/7/27 15:28
 * @description:
 */
@Tag(name = "账户管理接口")
@RestController
@RequestMapping("/api/account/userAccount")
public class AccountController {

    @Resource
    private UserAccountService userAccountService;

    @TingShuLogin
    @Operation(summary = "获取用户可用余额")
    @GetMapping("/getAvailableAmount")
    public RetVal<BigDecimal> getAvailableAmount() {
        Long userId = AuthContextHolder.getUserId();
        LambdaQueryWrapper<UserAccount> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserAccount::getUserId, userId);
        wrapper.select(UserAccount::getAvailableAmount);
        UserAccount userAccount = userAccountService.getOne(wrapper);
        BigDecimal availableAmount = userAccount.getAvailableAmount();
        return RetVal.ok(availableAmount);
    }

    @TingShuLogin
    @Operation(summary = "锁定账户金额")
    @PostMapping("/checkAndLock")
    public RetVal<Object> checkAndLock(@RequestBody AccountLockVo accountLockVo) {
        return userAccountService.checkAndLock(accountLockVo);
    }

}
