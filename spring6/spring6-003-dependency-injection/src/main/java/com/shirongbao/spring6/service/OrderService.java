package com.shirongbao.spring6.service;

import com.shirongbao.spring6.dao.OrderDao;

public class OrderService {

    private OrderDao orderDao;

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void generate(){
        orderDao.insert();
    }
}
