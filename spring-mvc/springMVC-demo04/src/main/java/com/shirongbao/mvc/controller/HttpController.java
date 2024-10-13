package com.shirongbao.mvc.controller;

import com.shirongbao.mvc.bean.User;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: ShiRongbao
 * Date: 2024/2/6
 * Time: 21:22
 */
@RestController  // 这个注解相当于给当前类添加了@Controller注解,并且为每一个控制器方法添加了@ResponseBody注解
public class HttpController {


    @RequestMapping("/testRequestBody")
    public String testRequestBody(@RequestBody String requestBody) {
        System.out.println("requestBody: " + requestBody);
        return "success";
    }

    @RequestMapping("/testRequestEntity")
    public String testRequestEntity(RequestEntity<String> entity) {
        System.out.println(entity.getHeaders());
        System.out.println(entity.getBody());
        return "success";
    }

    @RequestMapping("/testResponse")
    public void testResponse(HttpServletResponse response) throws Exception {
        response.getWriter().println("<h1>hello fuck you</h1>");
    }

    @RequestMapping("/testResponseBody")
    @ResponseBody
    public String testResponseBody() {
        return "success";
    }

    @RequestMapping("/testResponseUser")
    public User testResponseUser(){
        return new User(null, "zhangsan", "123456", 23, "男");
    }
}
