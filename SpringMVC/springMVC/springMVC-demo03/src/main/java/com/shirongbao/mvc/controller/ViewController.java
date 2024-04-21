package com.shirongbao.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ShiRongbao
 * @Create 2024/2/5 19:18
 */
@Controller
public class ViewController {

    @RequestMapping("/testThymeleafView")
    public String testThymeleafView(){
        System.out.println("跳转到ThymeleafView了");
        return "success";
    }

    @RequestMapping("/testForward")
    public String testForward(){
        return "forward:/testThymeleafView";
    }

    @RequestMapping("/testRedirect")
    public String testRedirect(){
        return "redirect:/testThymeleafView";
    }
}
