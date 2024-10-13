package com.shirongbao.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ShiRongbao
 * @create 2024/10/4
 * @description:
 */
@RestController
public class UserController {


    @GetMapping("/test")
    public String test() {
        return "hello world";
    }

}
