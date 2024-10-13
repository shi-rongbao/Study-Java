package com.shirongbao.spring6.web;

import com.shirongbao.spring6.service.UserService;
import com.shirongbao.spring6.service.impl.UserServiceImpl;

/**
 * 表示层
 */
public class UserAction {
    // private UserService userService = new UserServiceImpl();  violate DIP
    private UserService userService;

    /**
     * delete user info request
     */
    public void deleteRequest(){
        userService.deleteUser();
    }
}
