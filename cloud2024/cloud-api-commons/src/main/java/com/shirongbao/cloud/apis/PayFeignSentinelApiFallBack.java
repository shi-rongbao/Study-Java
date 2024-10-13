package com.shirongbao.cloud.apis;

import com.shirongbao.cloud.resp.ResultData;
import com.shirongbao.cloud.resp.ReturnCodeEnum;
import org.springframework.stereotype.Component;

/**
 * @author ShiRongbao
 * @create 2024/3/21 22:15
 * @description:
 */
@Component
public class PayFeignSentinelApiFallBack implements PayFeignSentinelApi {
    @Override
    public ResultData getPayByOrderNo(String orderNo) {
        return ResultData.fail(ReturnCodeEnum.RC500.getCode(), "对方服务宕机或不可用，FallBack服务降级o(╥﹏╥)o");
    }
}
