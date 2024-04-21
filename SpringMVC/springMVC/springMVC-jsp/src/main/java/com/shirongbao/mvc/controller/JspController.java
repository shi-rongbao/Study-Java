package com.shirongbao.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ShiRongbao
 * @Create 2024/2/5 21:39
 */
@Controller
public class JspController {

    @RequestMapping("/success")
    public String success() {
        return "success";
    }

}
