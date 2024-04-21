package com.shirongbao.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author ShiRongbao
 * @Create 2024/2/1 10:56
 */
@Controller
@RequestMapping("/hello")
public class RequestMappingController {
    @RequestMapping(
            // 请求首先会匹配请求地址,如果匹配到了当前地址那么再通过method来匹配请求方式
            value = {"/testRequestMapping"},
            // 如果不写method,则不限制请求方式,这里是限制只有get方式才可以通过
            method = {RequestMethod.GET},
            // params = {} 数组形式,里面可以是  key |        key = value |             !key |         key != value
            //                               必须携带key    必须携带key且值为value     不能携带key     必须携带key但值不可以为value
            params = {"username=admin"})
    public String success() {
        return "success";
    }

    // 在层级结构目录中使用{}占位传值
    @RequestMapping("/testPath/{id}/{username}")
    // 获取到值的方法是通过形参,但是要使用@PathVariable注解对应到上面占位的值
    public String testPath(@PathVariable("id") Integer id, @PathVariable("username") String username) {
        System.out.println("id:" + id + ", username:" + username);
        return "success";
    }


    // @GetMapping 注解简化了RequestMapping, 这个注解就等于RequestMapping中的method = "RequestMethod.GET"
    // 只能匹配get请求,但前提是要满足匹配到了/testRequestMapping路径
    @GetMapping("/testRequestMapping")
    public String testGetMapping() {
        return "success";
    }

    // 同上,但是匹配POST请求
    @PostMapping("/testRequestMapping")
    public String testPostMapping() {
        return "success";
    }
}
