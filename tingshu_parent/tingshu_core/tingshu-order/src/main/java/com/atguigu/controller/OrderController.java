package com.atguigu.controller;

import com.atguigu.entity.OrderInfo;
import com.atguigu.login.TingShuLogin;
import com.atguigu.result.RetVal;
import com.atguigu.service.OrderInfoService;
import com.atguigu.vo.OrderInfoVo;
import com.atguigu.vo.TradeVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author ShiRongbao
 * @create 2024/7/26 23:01
 * @description:
 */
@Tag(name = "订单管理接口")
@RestController
@RequestMapping("api/order/orderInfo")
public class OrderController {

    @Resource
    private OrderInfoService orderInfoService;

    @TingShuLogin
    @Operation(summary = "确认订单接口")
    @PostMapping("/confirmOrder")
    public RetVal<OrderInfoVo> confirmOrder(@RequestBody TradeVo tradeVo) {
        OrderInfoVo orderInfoVo = orderInfoService.confirmOrder(tradeVo);
        return RetVal.ok(orderInfoVo);
    }

    @TingShuLogin
    @Operation(summary = "订单提交接口")
    @PostMapping("/submitOrder")
    public RetVal<Map<String, Object>> submitOrder(@RequestBody OrderInfoVo orderInfoVo) {
        Map<String, Object> retMap = orderInfoService.submitOrder(orderInfoVo);
        return RetVal.ok(retMap);
    }

    @Operation(summary = "根据orderNo查询orderInfo")
    @GetMapping("/getOrderInfo/{orderNo}")
    public RetVal<OrderInfo> getOrderInfo(@PathVariable String orderNo) {
        OrderInfo orderInfo = orderInfoService.getOrderAndDetail(orderNo);
        return RetVal.ok(orderInfo);
    }

    /*@Operation(summary = "数据校验测试")
    @PostMapping("/mySubmitOrder")
    public void mySubmitOrder(@RequestBody @Valid OrderInfo orderInfo){
        System.out.println(orderInfo);
    }*/

}
