package com.shirongbao.cloud.service.impl;

import com.shirongbao.cloud.apis.AccountFeignApi;
import com.shirongbao.cloud.apis.StorageFeignApi;
import com.shirongbao.cloud.entities.Order;
import com.shirongbao.cloud.mapper.OrderMapper;
import com.shirongbao.cloud.service.OrderService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * @author ShiRongbao
 * @create 2024/3/24 22:19
 * @description:
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource  // 订单微服务通过OpenFeign去调用库存微服务
    private StorageFeignApi storageFeignApi;

    @Resource  // 订单微服务通过OpenFeign去调用账户微服务
    private AccountFeignApi accountFeignApi;

    @Override
    @GlobalTransactional(name = "shirongbao-create-order", rollbackFor = Exception.class)
    public void create(Order order) {
        // xid全局事务id的检查，重要
        String xid = RootContext.getXID();

        // 1.新建订单
        log.info("===========================开始新建订单: " + "\t" + "xid:" + xid);
        // 订单新建时默认初始订单状态是0
        order.setStatus(0);
        int result = orderMapper.insertSelective(order);
        // 插入订单成功后获得插入mysql的实体对象
        Order orderFromDB = null;
        if (result > 0) {
            // 从mysql中查出刚插入的记录
            orderFromDB = orderMapper.selectOne(order);
            log.info("===========================新建订单成功， orderFromDB info： " + orderFromDB);
            System.out.println();
            // 扣减库存
            storageFeignApi.decrease(orderFromDB.getProductId(), orderFromDB.getCount());
            log.info("===========================订单微服务结束调用Storage库存，做扣减完成");
            System.out.println();
            // 扣减账户余额
            accountFeignApi.decrease(orderFromDB.getUserId(), orderFromDB.getMoney());
            log.info("===========================订单微服务结束调用Account账号，做扣减完成");
            System.out.println();
            // 修改订单状态
            log.info("===========================修改订单状态");
            orderFromDB.setStatus(1);

            //
            Example whereCondition = new Example(Order.class);
            Example.Criteria criteria = whereCondition.createCriteria();
            criteria.andEqualTo("userId", orderFromDB.getUserId());
            criteria.andEqualTo("status", 0);

            int updateResult = orderMapper.updateByExampleSelective(orderFromDB, whereCondition);

            log.info("===========================修改订单状态完成" + "\t" + updateResult);
            log.info("===========================orderFromDB info: " + orderFromDB);
        }
        System.out.println();
        log.info("===========================结束新建订单: " + "\t" + "xid:" + xid);

    }
}
