package org.shirongbao.service;

import org.shirongbao.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("orderService")
public class OrderService {

    @Autowired
    @Qualifier("orderDaoImplForOracle")  // 参数是哪个bean的id,这里就会注入哪个bean的对象
    private OrderDao orderDao;

    public void generate(){
        orderDao.insert();
    }
}
