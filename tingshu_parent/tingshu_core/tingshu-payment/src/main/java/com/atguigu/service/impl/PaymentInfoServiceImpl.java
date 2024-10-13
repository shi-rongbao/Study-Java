package com.atguigu.service.impl;


import com.atguigu.OrderFeignClient;
import com.atguigu.UserFeignClient;
import com.atguigu.config.WxConfig;
import com.atguigu.constant.SystemConstant;
import com.atguigu.entity.OrderInfo;
import com.atguigu.entity.PaymentInfo;
import com.atguigu.execption.GuiguException;
import com.atguigu.mapper.PaymentInfoMapper;
import com.atguigu.result.RetVal;
import com.atguigu.service.PaymentInfoService;
import com.atguigu.util.AuthContextHolder;
import com.atguigu.vo.UserInfoVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wechat.pay.java.core.RSAAutoCertificateConfig;
import com.wechat.pay.java.service.payments.jsapi.JsapiServiceExtension;
import com.wechat.pay.java.service.payments.jsapi.model.Amount;
import com.wechat.pay.java.service.payments.jsapi.model.Payer;
import com.wechat.pay.java.service.payments.jsapi.model.PrepayRequest;
import com.wechat.pay.java.service.payments.jsapi.model.PrepayWithRequestPaymentResponse;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 付款信息表 服务实现类
 * </p>
 *
 * @author 强哥
 * @since 2023-12-19
 */
@Service
public class PaymentInfoServiceImpl extends ServiceImpl<PaymentInfoMapper, PaymentInfo> implements PaymentInfoService {

    @Resource
    private RSAAutoCertificateConfig config;

    @Resource
    private WxConfig wxConfig;

    @Resource
    private UserFeignClient userFeignClient;

    @Resource
    private PaymentInfoService paymentInfoService;

    @Resource
    private OrderFeignClient orderFeignClient;

    @Override
    public RetVal<Map<String, Object>> createJsapi(String paymentType, String orderNo) {
        try {
            Long userId = AuthContextHolder.getUserId();
            OrderInfo orderInfo = orderFeignClient.getOrderInfo(orderNo).getData();
            // 1.保存用户支付信息
            PaymentInfo paymentInfo = new PaymentInfo();
            paymentInfo.setUserId(userId);
            paymentInfo.setContent(orderInfo.getOrderTitle());
            paymentInfo.setAmount(orderInfo.getOrderAmount());
            paymentInfo.setPaymentType(paymentType);
            paymentInfo.setOrderNo(orderNo);
            paymentInfo.setPaymentStatus(SystemConstant.PAYMENT_UNPAID);
            paymentInfoService.save(paymentInfo);
            JsapiServiceExtension service = new JsapiServiceExtension.Builder().config(config).build();
            // 2.跟之前下单示例一样，填充预下单参数
            PrepayRequest request = new PrepayRequest();
            Amount amount = new Amount();
            // 以分为单位，这里测试，1分足矣
            amount.setTotal(1);
            request.setAmount(amount);
            request.setAppid(wxConfig.getAppid());
            request.setMchid(wxConfig.getMerchantId());
            request.setDescription(paymentInfo.getContent());
            request.setNotifyUrl(wxConfig.getNotifyUrl());
            request.setOutTradeNo(orderNo);
            // 3.获取用户信息
            UserInfoVo userInfoVo = userFeignClient.getUserInfo(userId).getData();
            Payer payer = new Payer();
            payer.setOpenid(userInfoVo.getWxOpenId());
            request.setPayer(payer);
            // 4.response包含了调起支付所需的所有参数，可以直接用于前端调期支付
            PrepayWithRequestPaymentResponse response = service.prepayWithRequestPayment(request);
            // 从response中获取页面需要的内容返回给前端
            Map<String, Object> result = new HashMap<>();
            result.put("timeStamp", response.getTimeStamp());
            result.put("nonceStr", response.getNonceStr());
            result.put("package", response.getPackageVal());
            result.put("signType", response.getSignType());
            result.put("paySign", response.getPaySign());
            return RetVal.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GuiguException(201, e.getMessage());
        }
    }

}
