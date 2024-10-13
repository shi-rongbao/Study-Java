package com.atguigu.controller;

import com.atguigu.login.TingShuLogin;
import com.atguigu.result.RetVal;
import com.atguigu.service.PaymentInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 付款信息表 前端控制器
 * </p>
 *
 * @author 强哥
 * @since 2023-12-19
 */
@Tag(name = "微信支付接口")
@RestController
@RequestMapping("api/payment/wxPay")
@Slf4j
public class PaymentInfoController {

    @Resource
    private PaymentInfoService paymentInfoService;

    @TingShuLogin
    @Operation(summary = "微信下单并返回二维码")
    @PostMapping("/createJsapi/{paymentType}/{orderNo}")
    public RetVal<Map<String, Object>> createJsapi(@PathVariable String paymentType, @PathVariable String orderNo) {
        return paymentInfoService.createJsapi(paymentType, orderNo);
    }

}
