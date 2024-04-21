package com.shirongbao.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ShiRongbao
 * @Create 2024/2/5 15:14
 */
@Controller
public class TestController {

    // 可以在springMVC.xml文件中使用mvc:view-controller标签简化,仅适用于控制器方法不做其他用途,仅仅用于访问请求通过springMVC视图解析器定位的视图
    // <mvc:view-controller path="/" view-name="index"/>
    // path对应的@RequestMapping注解中的value属性的值
    // view-name对应的该控制器方法的返回值
    /*@RequestMapping("/")
    public String toIndex(){
        return "index";
    }*/

    @RequestMapping("/test_view")
    public String toTestView(){
        return "test_view";
    }
}
