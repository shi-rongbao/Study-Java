package com.shirongbao.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ShiRongbao
 * @create 2024/2/8 22:54
 * @description:
 */

// @ResponseBody
// @Controller
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "Hello SpringBoot3";
    }

}
