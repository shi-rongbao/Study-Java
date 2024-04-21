package com.shirongbao.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ShiRongbao
 * @Create 2024/1/31 19:42
 */
@Controller
public class TestController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/param")
    public String param(){
        return "test_param";
    }

}

