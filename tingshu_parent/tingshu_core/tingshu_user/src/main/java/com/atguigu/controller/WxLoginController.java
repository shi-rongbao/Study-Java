package com.atguigu.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.atguigu.constant.RedisConstant;
import com.atguigu.entity.UserInfo;
import com.atguigu.login.TingShuLogin;
import com.atguigu.result.RetVal;
import com.atguigu.service.UserInfoService;
import com.atguigu.util.AuthContextHolder;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * vip服务配置表 前端控制器
 * </p>
 *
 * @author ShiRongbao
 * @since 2024-05-13
 */
@RestController
@RequestMapping("/api/user/wxLogin")
public class WxLoginController {

    @Resource
    private WxMaService wxMaService;

    @Resource
    private UserInfoService userInfoService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @TingShuLogin
    @Operation(summary = "获取用户信息")
    @GetMapping("/getUserInfo")
    public RetVal<UserInfo> getUserInfo(){
        Long userId = AuthContextHolder.getUserId();
        UserInfo userInfo = userInfoService.getById(userId);
        return RetVal.ok(userInfo);
    }

    @Operation(summary = "小程序授权登录")
    @GetMapping("/wxLogin/{code}")
    public RetVal<Map<String, Object>> wxLogin(@PathVariable(value = "code") String code) throws Exception {
        // 获取用户的openId
        WxMaJscode2SessionResult sessionInfo = wxMaService.getUserService().getSessionInfo(code);
        String openId = sessionInfo.getOpenid();
        // 从数据库中查询用户信息是否存在
        // QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        // 列名不写死，防止修改数据库字段名
        LambdaQueryWrapper<UserInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserInfo::getWxOpenId, openId);
        UserInfo userInfo = userInfoService.getOne(wrapper);
        // 如果认证失败，不存在，往数据库中添加信息（注册）
        if (userInfo == null) {
            // new新的UserInfo对象，赋值然后存入数据库
            userInfo = new UserInfo();
            userInfo.setNickname("听友" + System.currentTimeMillis());
            userInfo.setAvatarUrl("http://192.168.126.131:9000/tingshu/dog.jpg");
            userInfo.setWxOpenId(openId);
            userInfo.setIsVip(0);
            userInfoService.save(userInfo);
            // 这里少了创建用户金额表的数据
        }
        // 往redis里面存储用户信息
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String userKey = RedisConstant.USER_LOGIN_KEY_PREFIX + uuid;
        redisTemplate.opsForValue().set(userKey, userInfo, RedisConstant.USER_LOGIN_KEY_TIMEOUT, TimeUnit.SECONDS);
        Map<String, Object> retMap = new HashMap<>();
        retMap.put("token", uuid);
        return RetVal.ok(retMap);
    }

    @TingShuLogin
    @Operation(summary = "更新用户信息")
    @PostMapping("/updateUser")
    public RetVal<UserInfo> updateUser(@RequestBody UserInfo userInfo){
        Long userId = AuthContextHolder.getUserId();
        UserInfo oldUserInfo = userInfoService.getById(userId);
        BeanUtils.copyProperties(userInfo, oldUserInfo);
        oldUserInfo.setId(userId);
        userInfoService.updateById(oldUserInfo);
        return RetVal.ok(oldUserInfo);
    }

}
