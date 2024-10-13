package com.shirongbao.spring6.service;

import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {
    public void login(){
        System.out.println("正在校验用户登录");
    }

    public void logout(){
        System.out.println("用户正在退出");
    }
}
