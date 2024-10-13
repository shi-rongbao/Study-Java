package com.shirongbao.boot.ssm.controller;

import com.shirongbao.boot.ssm.bean.TUser;
import com.shirongbao.boot.ssm.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ShiRongbao
 * @create 2024/2/22 16:55
 * @description:
 */
@RestController
public class UserController {

    @Autowired
    UserMapper userMapper;

    @GetMapping("/user/{id}")
    public TUser getUser(@PathVariable("id") Long id){
        TUser user = userMapper.getUserById(id);
        return user;
    }

    @GetMapping("/user/test")
    public void insertTest(){
        userMapper.insertTest();
    }
}
