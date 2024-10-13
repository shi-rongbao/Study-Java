package com.shirongbao.interview.service.impl;


import com.shirongbao.interview.service.TestService;
import org.springframework.stereotype.Service;

/**
 * @author ShiRongbao
 * @create 2024/10/8
 * @description:
 */
@Service
public class TestServiceImpl implements TestService {
    @Override
    public int test() {
        System.out.println(1);
        return 1;
    }
}
