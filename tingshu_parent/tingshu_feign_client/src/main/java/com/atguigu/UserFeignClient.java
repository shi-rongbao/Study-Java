package com.atguigu;

import com.atguigu.entity.UserInfo;
import com.atguigu.fallback.UserFallBack;
import com.atguigu.result.RetVal;
import com.atguigu.vo.UserInfoVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * @author ShiRongbao
 * @create 2024/7/17 13:04
 * @description:
 */

@FeignClient(value = "tingshu-user", fallback = UserFallBack.class)
public interface UserFeignClient {

    @GetMapping("/api/user/userInfo/getUserInfo/{userId}")
    RetVal<UserInfoVo> getUserInfo(@PathVariable Long userId);

    @PostMapping("/api/user/userInfo/getUserShowPaidMarkOrNot/{albumId}")
    RetVal<Map<Long, Boolean>> getUserShowPaidMarkOrNot(@PathVariable Long albumId, @RequestBody List<Long> needPayTrackIdList);

    @GetMapping("/api/user/userInfo/getPaidTrackIdList/{albumId}")
    RetVal<List<Long>> getPaidTrackIdList(@PathVariable Long albumId);

}
