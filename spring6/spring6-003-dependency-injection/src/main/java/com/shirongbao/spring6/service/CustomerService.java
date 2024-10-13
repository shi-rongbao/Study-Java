package com.shirongbao.spring6.service;

import com.shirongbao.spring6.dao.UserDao;
import com.shirongbao.spring6.dao.VipDao;

public class CustomerService {
    private UserDao userDao;
    private VipDao vipDao;

    public CustomerService() {
    }

    public CustomerService(UserDao userDao, VipDao vipDao) {
        this.userDao = userDao;
        this.vipDao = vipDao;
    }

    public void save(){
        userDao.insert();
        vipDao.insert();
    }
}
