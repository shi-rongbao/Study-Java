package com.atguigu.consumer;

import com.atguigu.constant.KafkaConstant;
import com.atguigu.service.OrderInfoService;
import jakarta.annotation.Resource;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author ShiRongbao
 * @create 2024/7/30 17:17
 * @description:
 */
@Component
public class OrderConsumer {

    @Resource
    private OrderInfoService orderInfoService;

    @KafkaListener(topics = KafkaConstant.QUEUE_ORDER_CANCEL)
    public void cancelOrder(Long orderId){
        if (orderId != null) {
            orderInfoService.cancelOrder(orderId);
        }
    }
}
