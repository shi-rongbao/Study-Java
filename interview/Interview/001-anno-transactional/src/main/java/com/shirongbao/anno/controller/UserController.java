package com.shirongbao.anno.controller;

import com.shirongbao.anno.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ShiRongbao
 * @create 2024/10/8
 * @description:
 */
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService ;

    @GetMapping("/user")
    public void testDemo(){
        userService.insertOne();
    }

}
