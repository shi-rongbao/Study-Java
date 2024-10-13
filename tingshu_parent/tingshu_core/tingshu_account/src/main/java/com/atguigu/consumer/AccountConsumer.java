package com.atguigu.consumer;

import com.alibaba.csp.sentinel.util.StringUtil;
import com.atguigu.constant.KafkaConstant;
import com.atguigu.service.UserAccountService;
import jakarta.annotation.Resource;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author ShiRongbao
 * @create 2024/7/30 17:40
 * @description:
 */
@Component
public class AccountConsumer {

    @Resource
    private UserAccountService userAccountService;

    @KafkaListener(topics = KafkaConstant.DEDUCT_LOCK_ACCOUNT_QUEUE)
    public void deductLockAccount(String orderNo){
        if (StringUtil.isNotBlank(orderNo)) {
            userAccountService.deductLockAccount(orderNo);
        }
    }

    @KafkaListener(topics = KafkaConstant.UNLOCK_ACCOUNT_QUEUE)
    public void unlockAccount(String orderNo){
        if (StringUtil.isNotBlank(orderNo)) {
            userAccountService.unlockAccount(orderNo);
        }
    }
}
