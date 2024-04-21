package com.shirongbao.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Created by IntelliJ IDEA.
 * @Author: ShiRongbao
 * @Date: 2024/2/8
 * @Time: 17:04
 */

@Controller
public class TestController {

    @RequestMapping("/")
    public String index(){
        /*String name = null;
        name.toUpperCase();*/
        return "index";
    }

}
