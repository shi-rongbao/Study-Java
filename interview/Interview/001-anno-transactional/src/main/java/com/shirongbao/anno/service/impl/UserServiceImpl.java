package com.shirongbao.anno.service.impl;

import com.shirongbao.anno.entity.User;
import com.shirongbao.anno.mapper.UserMapper;
import com.shirongbao.anno.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author ShiRongbao
 * @create 2024/10/8
 * @description:
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Transactional
    @Override
    public void insertOne() {
        User user = new User();
        user.setUsername("shirongbao");
        user.setPassword("123456");
        user.setEmail("shirong");
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        userMapper.insertOne(user);
    }

}
