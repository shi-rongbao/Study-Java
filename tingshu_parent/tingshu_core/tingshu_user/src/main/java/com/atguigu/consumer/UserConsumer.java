package com.atguigu.consumer;

import com.alibaba.csp.sentinel.util.StringUtil;
import com.alibaba.fastjson.JSON;
import com.atguigu.constant.KafkaConstant;
import com.atguigu.service.UserInfoService;
import com.atguigu.vo.UserPaidRecordVo;
import jakarta.annotation.Resource;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author ShiRongbao
 * @create 2024/7/28 21:34
 * @description:
 */
@Component
public class UserConsumer {

    @Resource
    private UserInfoService userInfoService;

    @KafkaListener(topics = KafkaConstant.USER_PAID_QUEUE)
    public void updateUserPaidRecord(String data) {
        if (StringUtil.isNotBlank(data)) {
            UserPaidRecordVo userPaidRecordVo = JSON.parseObject(data, UserPaidRecordVo.class);
            // 更新用户支付信息
            userInfoService.updateUserPaidInfo(userPaidRecordVo);
        }
    }

}
