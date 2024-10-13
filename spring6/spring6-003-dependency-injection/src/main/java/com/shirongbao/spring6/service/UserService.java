package com.shirongbao.spring6.service;

import com.shirongbao.spring6.dao.UserDao;
import com.shirongbao.spring6.dao.VipDao;

public class UserService {
    private UserDao userDao;
    private VipDao vipDao;
    // set注入必须要提供一个set方法
    // spring容器会调用这个set方法给userDao属性赋值


    public void setVipDao(VipDao vipDao) {
        this.vipDao = vipDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void saveUser(){
        userDao.insert();
    }
}
