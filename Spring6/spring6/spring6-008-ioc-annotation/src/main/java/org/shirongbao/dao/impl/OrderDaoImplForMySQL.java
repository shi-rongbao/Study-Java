package org.shirongbao.dao.impl;

import org.shirongbao.dao.OrderDao;
import org.springframework.stereotype.Repository;

@Repository("orderDaoImplForMySQL")
public class OrderDaoImplForMySQL implements OrderDao {
    @Override
    public void insert() {
        System.out.println("MySQL数据库正在保存订单信息...");
    }
}
