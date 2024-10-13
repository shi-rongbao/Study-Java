package com.atguigu.service.impl;

import com.alibaba.fastjson.JSON;
import com.atguigu.AccountFeignClient;
import com.atguigu.AlbumFeignClient;
import com.atguigu.UserFeignClient;
import com.atguigu.VipFeignClient;
import com.atguigu.constant.KafkaConstant;
import com.atguigu.constant.SystemConstant;
import com.atguigu.entity.*;
import com.atguigu.execption.GuiguException;
import com.atguigu.helper.SignHelper;
import com.atguigu.mapper.OrderInfoMapper;
import com.atguigu.result.ResultCodeEnum;
import com.atguigu.result.RetVal;
import com.atguigu.service.KafkaService;
import com.atguigu.service.OrderDerateService;
import com.atguigu.service.OrderDetailService;
import com.atguigu.service.OrderInfoService;
import com.atguigu.util.AuthContextHolder;
import com.atguigu.vo.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBlockingDeque;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * <p>
 * 订单信息 服务实现类
 * </p>
 *
 * @author 强哥
 * @since 2023-12-15
 */
@Service
@Slf4j
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {

    @Resource
    private AlbumFeignClient albumFeignClient;

    @Resource
    private UserFeignClient userFeignClient;

    @Resource
    private VipFeignClient vipFeignClient;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Resource
    private AccountFeignClient accountFeignClient;

    @Resource
    private OrderDetailService orderDetailService;

    @Resource
    private OrderDerateService orderDerateService;

    @Resource
    private KafkaService kafkaService;

    @Resource
    private RedissonClient redissonClient;

    @Override
    public OrderInfoVo confirmOrder(TradeVo tradeVo) {
        Long userId = AuthContextHolder.getUserId();
        UserInfoVo userInfoVo = userFeignClient.getUserInfo(userId).getData();
        BigDecimal originalPrice = new BigDecimal("0");
        BigDecimal finalPrice = new BigDecimal("0");
        BigDecimal deratePrice = new BigDecimal("0");
        List<OrderDerateVo> orderDerateVolist = new ArrayList<>();
        List<OrderDetailVo> orderDetailVoList = new ArrayList<>();
        String itemType = tradeVo.getItemType();
        // 购买整个专辑
        Integer isVip = userInfoVo.getIsVip();
        if (SystemConstant.BUY_ALBUM.equals(itemType)) {
            Long albumId = tradeVo.getItemId();
            AlbumInfo albumInfo = albumFeignClient.getAlbumInfoById(albumId).getData();
            originalPrice = albumInfo.getPrice();
            finalPrice = originalPrice;
            deratePrice = originalPrice.subtract(finalPrice);
            if (isVip == 0) {
                // 如果用户不是VIP
                // 如果专辑可以打折，计算最终价格  -1不打折
                if (albumInfo.getDiscount().intValue() != -1) {
                    finalPrice = originalPrice.multiply(albumInfo.getDiscount()).divide(new BigDecimal(10));
                    deratePrice = originalPrice.subtract(finalPrice);
                }
            } else {
                // 如果用户是VIP
                // 如果专辑可以打折，计算最终价格  -1不打折
                if (albumInfo.getVipDiscount().intValue() != -1) {
                    finalPrice = originalPrice.multiply(albumInfo.getVipDiscount()).divide(new BigDecimal(10));
                    deratePrice = originalPrice.subtract(finalPrice);
                }
            }
            OrderDetailVo orderDetailVo = new OrderDetailVo();
            orderDetailVo.setItemId(albumId);
            orderDetailVo.setItemName(albumInfo.getAlbumTitle());
            orderDetailVo.setItemUrl(albumInfo.getCoverUrl());
            orderDetailVo.setItemPrice(finalPrice);
            orderDetailVoList.add(orderDetailVo);
            if (deratePrice.intValue() != 0) {
                OrderDerateVo orderDerateVo = new OrderDerateVo();
                orderDerateVo.setDerateType(SystemConstant.ALBUM_DISCOUNT);
                orderDerateVo.setDerateAmount(deratePrice);
                orderDerateVolist.add(orderDerateVo);
            }
        }
        // 购买VIP
        else if (SystemConstant.BUY_VIP.equals(itemType)) {
            Long vipServiceConfigId = tradeVo.getItemId();
            VipServiceConfig vipServiceConfig = vipFeignClient.getVipServiceConfigById(vipServiceConfigId).getData();
            originalPrice = vipServiceConfig.getPrice();
            finalPrice = vipServiceConfig.getDiscountPrice();
            deratePrice = originalPrice.subtract(finalPrice);
            OrderDetailVo orderDetailVo = new OrderDetailVo();
            orderDetailVo.setItemId(vipServiceConfigId);
            orderDetailVo.setItemName("VIP会员：" + vipServiceConfig.getName());
            orderDetailVo.setItemUrl(vipServiceConfig.getImageUrl());
            orderDetailVo.setItemPrice(finalPrice);
            orderDetailVoList.add(orderDetailVo);
            if (deratePrice.intValue() != 0) {
                OrderDerateVo orderDerateVo = new OrderDerateVo();
                orderDerateVo.setDerateType(SystemConstant.ORDER_DERATE_VIP_SERVICE_DISCOUNT);
                orderDerateVo.setDerateAmount(deratePrice);
                orderDerateVolist.add(orderDerateVo);
            }
        }
        // 购买声音
        else if (SystemConstant.BUY_TRACK.equals(itemType)) {
            Integer trackCount = tradeVo.getTrackCount();
            if (trackCount < 0) {
                throw new GuiguException(ResultCodeEnum.ARGUMENT_VALID_ERROR);
            }
            Long trackId = tradeVo.getItemId();
            List<TrackInfo> prepareToBuyTrackList = albumFeignClient.getTrackListPrepareToBuy(trackId, trackCount).getData();
            Long albumId = prepareToBuyTrackList.get(0).getAlbumId();
            AlbumInfo albumInfo = albumFeignClient.getAlbumInfoById(albumId).getData();
            if (isVip == 0) {
                // 如果用户不是VIP
                // 如果专辑可以打折，计算最终价格  -1不打折
                if (albumInfo.getDiscount().intValue() != -1) {
                    // 购买多个声音
                    if (trackCount > 0) {
                        // 计算原始价格
                        originalPrice = albumInfo.getPrice().multiply(new BigDecimal(trackCount));
                        // 计算打折价格
                        finalPrice = albumInfo.getPrice().multiply(new BigDecimal(trackCount)).multiply(albumInfo.getDiscount()).divide(new BigDecimal(10));
                    } else {
                        originalPrice = albumInfo.getPrice();
                        finalPrice = albumInfo.getPrice().multiply(albumInfo.getDiscount()).divide(new BigDecimal(10));
                    }
                } else {
                    // 购买多个声音
                    if (trackCount > 0) {
                        // 计算原始价格
                        originalPrice = albumInfo.getPrice().multiply(new BigDecimal(trackCount));
                        // 计算打折价格
                    } else {
                        originalPrice = albumInfo.getPrice();
                    }
                    finalPrice = originalPrice;
                }
                deratePrice = originalPrice.subtract(finalPrice);
                orderDetailVoList = getOrderDetailVoToForTrackOrder(prepareToBuyTrackList, albumInfo);
                if (deratePrice.intValue() != 0) {
                    OrderDerateVo orderDerateVo = new OrderDerateVo();
                    orderDerateVo.setDerateType(SystemConstant.ALBUM_DISCOUNT);
                    orderDerateVo.setDerateAmount(deratePrice);
                    orderDerateVolist.add(orderDerateVo);
                }
            } else {
                // 如果用户是VIP
                // 如果专辑可以打折，计算最终价格  -1不打折
                // 购买多个声音
                if (trackCount > 0) {
                    // 计算原始价格
                    originalPrice = albumInfo.getPrice().multiply(new BigDecimal(trackCount));
                    // 计算打折价格
                    finalPrice = albumInfo.getPrice().multiply(new BigDecimal(trackCount)).multiply(albumInfo.getVipDiscount()).divide(new BigDecimal(10));
                } else {
                    originalPrice = albumInfo.getPrice();
                    finalPrice = albumInfo.getPrice().multiply(albumInfo.getVipDiscount()).divide(new BigDecimal(10));
                }
                deratePrice = originalPrice.subtract(finalPrice);
                orderDetailVoList = getOrderDetailVoToForTrackOrder(prepareToBuyTrackList, albumInfo);
            }
        }
        // 生成一个tradeNo，防止重复提交订单
        String tradeNoKey = "user:trade:" + userId;
        String tradeNo = UUID.randomUUID().toString().replaceAll("-", "");
        redisTemplate.opsForValue().set(tradeNoKey, tradeNo);
        OrderInfoVo orderInfoVo = new OrderInfoVo();
        orderInfoVo.setItemType(itemType);
        orderInfoVo.setOriginalAmount(originalPrice);
        orderInfoVo.setDerateAmount(deratePrice);
        orderInfoVo.setTradeNo(tradeNo);
        orderInfoVo.setOrderAmount(finalPrice);
        orderInfoVo.setOrderDerateVoList(orderDerateVolist);
        orderInfoVo.setOrderDetailVoList(orderDetailVoList);
        orderInfoVo.setTimestamp(SignHelper.getTimestamp());
        orderInfoVo.setPayWay("");
        // 生成签名
        Map<String, Object> map = JSON.parseObject(JSON.toJSONString(orderInfoVo), Map.class);
        String sign = SignHelper.getSign(map);
        orderInfoVo.setSign(sign);
        return orderInfoVo;
    }

    @Override
    @Transactional
    public Map<String, Object> submitOrder(OrderInfoVo orderInfoVo) {
        Long userId = AuthContextHolder.getUserId();
        // 校验签名是否被篡改
        Map<String, Object> map = JSON.parseObject(JSON.toJSONString(orderInfoVo), Map.class);
        map.put("payWay", "");
        // 检查签名是否被更改，如果被更改了直接报错
        SignHelper.checkSign(map);
        String tradeNo = orderInfoVo.getTradeNo();
        String tradeNoKey = "user:trade:" + userId;
        // 判断是否重复提交订单，如果重复提交订单就抛出异常
        String script = "if(redis.call('get', KEYS[1]) == ARGV[1]) then return redis.call('del', KEYS[1]) else return 0 end";
        DefaultRedisScript<Boolean> executeScript = new DefaultRedisScript<>(script, Boolean.class);
        Boolean flag = redisTemplate.execute(executeScript, List.of(tradeNoKey), tradeNo);
        // 如果重复提交订单就抛出异常
        if (Boolean.FALSE.equals(flag)) {
            log.error("重复提交订单");
            throw new GuiguException(ResultCodeEnum.REPEAT_SUBMIT_ORDER);
        }
        String orderNo = UUID.randomUUID().toString().replaceAll("-", "");
        // 余额支付
        if (SystemConstant.ACCOUNT_BALANCES.equals(orderInfoVo.getPayWay())) {
            try {
                AccountLockVo accountLockVo = new AccountLockVo();
                accountLockVo.setOrderNo(orderNo);
                accountLockVo.setUserId(userId);
                accountLockVo.setAmount(orderInfoVo.getOrderAmount());
                accountLockVo.setContent(orderInfoVo.getOrderDetailVoList().get(0).getItemName());
                // 远程调用，检查余额是否充足并且锁定余额
                RetVal<Object> retVal = accountFeignClient.checkAndLock(accountLockVo);
                if (retVal.getCode() != 200) {
                    // 如果锁定失败，抛出异常
                    throw new GuiguException(retVal.getCode(), retVal.getMessage());
                }
                // 成功锁定余额后保存订单信息
                saveOrderInfo(orderInfoVo, orderNo);
                // 支付成功扣减库存，这里是虚拟商品，库存不用扣减，这里的业务是取消锁定金额，扣减金额
                // 发送消息，取消用户余额锁定，同时扣减余额
                kafkaService.sendMessage(KafkaConstant.DEDUCT_LOCK_ACCOUNT_QUEUE, orderNo);
            } catch (GuiguException e) {
                e.printStackTrace();
                throw new GuiguException(e.getCode(), e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                // 如果执行中出现异常，也要取消用户余额锁定，同时报错
                kafkaService.sendMessage(KafkaConstant.UNLOCK_ACCOUNT_QUEUE, orderNo);
            }
        } else {
            // 其他方式支付
            saveOrderInfo(orderInfoVo, orderNo);
        }
        // 最后返回数据
        Map<String, Object> retMap = new HashMap<>();
        retMap.put("orderNo", orderNo);
        return retMap;
    }

    @Override
    public void cancelOrder(Long orderId) {
        if (orderId != null) {
            OrderInfo orderInfo = getById(orderId);
            if (SystemConstant.ORDER_UNPAID.equals(orderInfo.getOrderStatus())) {
                // 修改order_info表，将订单信息修改为已取消
                orderInfo.setOrderStatus(SystemConstant.ORDER_STATUS_CANCEL);
                orderInfo.setUpdateTime(new Date());
                orderInfo.setIsDeleted(1);
                updateById(orderInfo);
            }
        }
    }

    /**
     * 根据传入的orderInfoVo和orderNo保存订单基本信息，包括保存订单信息，保存订单明细，保存订单减免
     *
     * @param orderInfoVo orderInfoVo
     * @param orderNo     orderNo
     */
    private void saveOrderInfo(OrderInfoVo orderInfoVo, String orderNo) {
        // 保存订单基本信息
        Long userId = AuthContextHolder.getUserId();
        List<OrderDetailVo> orderDetailVoList = orderInfoVo.getOrderDetailVoList();
        OrderInfo orderInfo = new OrderInfo();
        BeanUtils.copyProperties(orderInfoVo, orderInfo);
        orderInfo.setOrderNo(orderNo);
        if (!CollectionUtils.isEmpty(orderDetailVoList)) {
            String orderTitle = orderDetailVoList.get(0).getItemName();
            orderInfo.setOrderTitle(orderTitle);
        }
        orderInfo.setUserId(userId);
        orderInfo.setOrderStatus(SystemConstant.ORDER_UNPAID);
        save(orderInfo);
        // 保存订单明细信息
        Long orderId = orderInfo.getId();
        if (!CollectionUtils.isEmpty(orderDetailVoList)) {
            List<OrderDetail> orderDetailList = orderDetailVoList.stream().map(orderDetailVo -> {
                OrderDetail orderDetail = new OrderDetail();
                BeanUtils.copyProperties(orderDetailVo, orderDetail);
                orderDetail.setOrderId(orderId);
                return orderDetail;
            }).toList();
            // 批量保存订单明细
            orderDetailService.saveBatch(orderDetailList);
        }
        // 保存订单减免信息
        List<OrderDerateVo> orderDerateVoList = orderInfoVo.getOrderDerateVoList();
        if (!CollectionUtils.isEmpty(orderDerateVoList)) {
            List<OrderDerate> orderDerateList = orderDerateVoList.stream().map(orderDerateVo -> {
                OrderDerate orderDerate = new OrderDerate();
                BeanUtils.copyProperties(orderDerateVo, orderDerate);
                orderDerate.setOrderId(orderId);
                return orderDerate;
            }).toList();
            // 批量保存订单减免信息
            orderDerateService.saveBatch(orderDerateList);
        }
        // 判断是否余额支付
        if (SystemConstant.ACCOUNT_BALANCES.equals(orderInfoVo.getPayWay())) {
            // 再次判断是否是余额支付，如果是，执行支付成功
            afterPaySuccess(orderNo);
        } else {
            // 订单超时取消
            sendDelayMessage(orderId);
        }
    }

    /**
     * 根据orderId，定时取消超时订单
     *
     * @param orderId orderId
     */
    private void sendDelayMessage(Long orderId) {
        // 阻塞队列
        RBlockingDeque<Object> blockingDeque = redissonClient.getBlockingDeque(KafkaConstant.QUEUE_ORDER_CANCEL);
        RDelayedQueue<Object> delayedQueue = redissonClient.getDelayedQueue(blockingDeque);
        // 定时发消息，取消超时订单
        delayedQueue.offer(orderId, KafkaConstant.DELAY_TIME, TimeUnit.SECONDS);
    }

    /**
     * 成功支付
     *
     * @param orderNo 订单号
     */
    private void afterPaySuccess(String orderNo) {
        // 更新订单信息为已支付
        LambdaQueryWrapper<OrderInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OrderInfo::getOrderNo, orderNo);
        OrderInfo updateOrderInfo = new OrderInfo();
        updateOrderInfo.setOrderStatus(SystemConstant.ORDER_PAID);
        update(updateOrderInfo, wrapper);
        // 发送消息队列保存用户支付记录
        OrderInfo orderInfo = getOrderAndDetail(orderNo);
        List<Long> itemIdList = orderInfo.getOrderDetailList().stream().map(OrderDetail::getItemId).toList();
        UserPaidRecordVo userPaidRecordVo = new UserPaidRecordVo();
        userPaidRecordVo.setUserId(orderInfo.getUserId());
        userPaidRecordVo.setOrderNo(orderNo);
        String itemType = orderInfo.getItemType();
        userPaidRecordVo.setItemType(itemType);
        userPaidRecordVo.setItemIdList(itemIdList);
        kafkaService.sendMessage(KafkaConstant.USER_PAID_QUEUE, JSON.toJSONString(userPaidRecordVo));
        // 发送消息队列更新专辑购买数量
        if (SystemConstant.BUY_ALBUM.equals(itemType) || SystemConstant.BUY_TRACK.equals(itemType)) {
            // 如果是购买声音或者购买专辑，都需要更新专辑购买数量
            Long albumId = null;
            if (SystemConstant.BUY_ALBUM.equals(itemType)) {
                albumId = itemIdList.get(0);
            } else {
                TrackInfo trackInfo = albumFeignClient.getTrackInfoById(itemIdList.get(0)).getData();
                albumId = trackInfo.getAlbumId();
            }
            AlbumStatMqVo albumStatMqVo = new AlbumStatMqVo();
            albumStatMqVo.setBusinessNo(UUID.randomUUID().toString().replaceAll("-", ""));
            albumStatMqVo.setAlbumId(albumId);
            albumStatMqVo.setStatType(SystemConstant.BUY_NUM_ALBUM);
            // 发送消息，更新album_stat表，把专辑购买量+1
            kafkaService.sendMessage(KafkaConstant.UPDATE_ALBUM_BUY_NUM_QUEUE, JSON.toJSONString(albumStatMqVo));
        }
    }

    /**
     * 根据orderNo 查询orderInfo表，查询orderInfo信息
     *
     * @param orderNo orderNo
     * @return 返回orderInfo对象
     */
    @Override
    public OrderInfo getOrderAndDetail(String orderNo) {
        LambdaQueryWrapper<OrderInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OrderInfo::getOrderNo, orderNo);
        OrderInfo orderInfo = getOne(wrapper);
        Long orderId = orderInfo.getId();
        List<OrderDetail> orderDetailList = orderDetailService.list(new LambdaQueryWrapper<OrderDetail>().eq(OrderDetail::getOrderId, orderId));
        List<OrderDerate> orderDerateList = orderDerateService.list(new LambdaQueryWrapper<OrderDerate>().eq(OrderDerate::getOrderId, orderId));
        orderInfo.setOrderDetailList(orderDetailList);
        orderInfo.setOrderDerateList(orderDerateList);
        orderInfo.setOrderStatusName(getOrderStatusName(orderInfo.getOrderStatus()));
        orderInfo.setPayWay(getPayWayName(orderInfo.getPayWay()));
        return orderInfo;
    }

    /**
     * 根据支付方式获取支付方式名
     *
     * @param payWay payWay
     * @return 返回支付方式名
     */
    private String getPayWayName(String payWay) {
        String payWayName = "";
        if (SystemConstant.ORDER_PAY_WAY_WEIXIN.equals(payWay)) {
            payWayName = "微信";
        } else if (SystemConstant.ORDER_PAY_WAY_ALIPAY.equals(payWay)) {
            payWayName = "支付宝";
        } else {
            payWayName = "余额";
        }
        return payWayName;
    }

    /**
     * 根据getOrderStatus获取支付方式名
     *
     * @param orderStatus orderStatus
     * @return 返回支付方式名
     */
    private String getOrderStatusName(String orderStatus) {
        String orderStatusName = "";
        if (SystemConstant.ORDER_UNPAID.equals(orderStatus)) {
            orderStatusName = "待支付";
        } else if (SystemConstant.ORDER_PAID.equals(orderStatus)) {
            orderStatusName = "已支付";
        } else {
            orderStatusName = "已取消";
        }
        return orderStatusName;
    }

    @NotNull
    private List<OrderDetailVo> getOrderDetailVoToForTrackOrder(List<TrackInfo> prepareToBuyTrackList, AlbumInfo albumInfo) {
        List<OrderDetailVo> orderDetailVoList;
        orderDetailVoList = prepareToBuyTrackList.stream().map(prepareToBuyTrack -> {
            OrderDetailVo orderDetailVo = new OrderDetailVo();
            orderDetailVo.setItemId(prepareToBuyTrack.getId());
            orderDetailVo.setItemName(prepareToBuyTrack.getTrackTitle());
            orderDetailVo.setItemUrl(prepareToBuyTrack.getCoverUrl());
            orderDetailVo.setItemPrice(albumInfo.getPrice());
            return orderDetailVo;
        }).collect(Collectors.toList());
        return orderDetailVoList;
    }
}
