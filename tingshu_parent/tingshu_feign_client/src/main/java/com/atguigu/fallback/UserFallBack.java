package com.atguigu.fallback;

import com.atguigu.UserFeignClient;
import com.atguigu.entity.UserInfo;
import com.atguigu.result.RetVal;
import com.atguigu.vo.UserInfoVo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ShiRongbao
 * @create 2024/7/17 13:05
 * @description:
 */
@Component
public class UserFallBack implements UserFeignClient {
    @Override
    public RetVal<UserInfoVo> getUserInfo(Long userId) {
        return RetVal.fail(new UserInfoVo()).message("对不起，系统正忙！");
    }

    @Override
    public RetVal<Map<Long, Boolean>> getUserShowPaidMarkOrNot(Long albumId, List<Long> needPayTrackIdList) {
        Map<Long, Boolean> map = new HashMap<>();
        return RetVal.fail(map).message("对不起，系统正忙！");
    }

    @Override
    public RetVal<List<Long>> getPaidTrackIdList(Long albumId) {
        List<Long> list = new ArrayList<>();
        return RetVal.fail(list).message("对不起，系统正忙！");
    }

}
