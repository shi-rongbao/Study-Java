package com.atguigu;

import com.atguigu.entity.OrderInfo;
import com.atguigu.result.RetVal;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author ShiRongbao
 * @create 2024/7/31 21:08
 * @description:
 */
@FeignClient(value = "tingshu-order")
public interface OrderFeignClient {

    @GetMapping("api/order/orderInfo/getOrderInfo/{orderNo}")
    RetVal<OrderInfo> getOrderInfo(@PathVariable String orderNo);
}
