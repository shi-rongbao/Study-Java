package com.shirongbao.spring6.service.impl;

import com.shirongbao.spring6.dao.UserDao;
import com.shirongbao.spring6.service.UserService;

public class UserServiceImpl implements UserService {
    // private UserDao user = new UserDaoImplForMySQL();  violate DIP

    private UserDao user;

    /**
     * delete user service
     */
    @Override
    public void deleteUser() {
        user.deleteById();
    }
}
