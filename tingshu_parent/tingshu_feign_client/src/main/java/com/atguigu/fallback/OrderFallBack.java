package com.atguigu.fallback;

import com.atguigu.OrderFeignClient;
import com.atguigu.entity.OrderInfo;
import com.atguigu.result.RetVal;
import org.springframework.stereotype.Component;

/**
 * @author ShiRongbao
 * @create 2024/7/31 21:09
 * @description:
 */
@Component
public class OrderFallBack implements OrderFeignClient {
    @Override
    public RetVal<OrderInfo> getOrderInfo(String orderNo) {
        return RetVal.fail(new OrderInfo()).message("对不起，系统正忙！");
    }
}
