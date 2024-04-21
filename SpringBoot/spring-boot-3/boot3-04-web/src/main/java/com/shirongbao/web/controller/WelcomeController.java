package com.shirongbao.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ShiRongbao
 * @create 2024/2/17 22:08
 * @description:
 */
@Controller
public class WelcomeController {

    @GetMapping("/well")
    public String hello(@RequestParam("username") String username, Model model){
        model.addAttribute("msg", username);
        /*int i = 10 / 0;  // 模拟错误*/
        return "Welcome";
    }

    /*// 如果自己写了异常处理,那么springboot按照自己写的异常处理
    @ResponseBody
    @ExceptionHandler
    public String handleException(Exception e){
        return "Something Wrong, 原因:" + e.getMessage();
    }*/
}
