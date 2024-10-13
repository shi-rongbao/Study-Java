package com.atguigu.service;

import com.atguigu.entity.OrderInfo;
import com.atguigu.vo.OrderInfoVo;
import com.atguigu.vo.TradeVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 订单信息 服务类
 * </p>
 *
 * @author 强哥
 * @since 2023-12-15
 */
public interface OrderInfoService extends IService<OrderInfo> {

    /**
     * 根据关键数据确认订单信息
     *
     * @param tradeVo 包括付款项目类型，付款项目id，针对购买声音，购买的集数有多少
     * @return 返回订单明细，包括付费类型，原始价格，打折价格，最终价格，
     */
    OrderInfoVo confirmOrder(TradeVo tradeVo);

    /**
     * 根据传来的订单信息，确认订单
     *
     * @param orderInfoVo 订单信息
     * @return 返回一个map集合，储存着orderNo
     */
    Map<String, Object> submitOrder(OrderInfoVo orderInfoVo);

    /**
     * 根据orderId取消订单
     * @param orderId orderId
     */
    void cancelOrder(Long orderId);

    /**
     * 根据orderNo茬新订单和订单明细
     * @param orderNo orderNo
     * @return 返回一个orderInfo对象
     */
    OrderInfo getOrderAndDetail(String orderNo);
}
