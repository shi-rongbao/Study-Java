package com.atguigu.service;

import com.atguigu.entity.PaymentInfo;
import com.atguigu.result.RetVal;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 付款信息表 服务类
 * </p>
 *
 * @author 强哥
 * @since 2023-12-19
 */
public interface PaymentInfoService extends IService<PaymentInfo> {

    /**
     * 微信下单并返回二维码
     *
     * @param paymentType 支付方式
     * @param orderNo     orderNo
     * @return 返回前端需要的内容
     */
    RetVal<Map<String, Object>> createJsapi(String paymentType, String orderNo);

}
