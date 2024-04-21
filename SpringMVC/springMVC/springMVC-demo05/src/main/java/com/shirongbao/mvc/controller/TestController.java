package com.shirongbao.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by IntelliJ IDEA.
 * User: ShiRongbao
 * Date: 2024/2/7
 * Time: 22:57
 */
@Controller
public class TestController {

    @RequestMapping("/testInterceptor")
    public String testInterceptor(){
        String name = null;
        name.toUpperCase();
        return "success";
    }
}
