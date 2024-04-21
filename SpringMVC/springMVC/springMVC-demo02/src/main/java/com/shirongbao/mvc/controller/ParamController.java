package com.shirongbao.mvc.controller;

import com.shirongbao.mvc.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ShiRongbao
 * @Create 2024/2/3 12:25
 */
@Controller
public class ParamController {

    // use Protogenetic ServletAPI Get Params
    // 使用原生的ServletAPI获取参数
    @RequestMapping("/testServletApi")
    public String testServletApi(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username);
        System.out.println(password);
        return "success";
    }

    // 使用控制器形参获取请求参数
    @RequestMapping("/testParam")
    // 只需要保证控制器方法的形参中的参数名与传入请求参数名保持一致,就可以自动赋值
    public String testParam(String username, String password) {
        System.out.println(username);
        System.out.println(password);
        return "success";
    }

    @RequestMapping("/testParam2")
    // 如果要接收多个参数,例如checkbox 可以使用字符串接收,结果会用逗号隔开,当然也可以使用数组接收
    // 注解RequestParam的value参数 -> 传入的哪个请求参数与当前形参对应
    // required参数,默认为true,代表必须存在这个请求参数,如果不存在则报错,如果改为false,那么如果不存在可以赋值默认
    // defaultValue -> 默认值,如果没有传入值那么自动将defaultValue的值赋值给形参,如果传入正常的参数则正常赋值,传入空字符串也是给默认值
    public String testParam2(@RequestParam(value = "username", required = false, defaultValue = "fuckYou") String username,
                             @RequestParam("password") String password,
                             @RequestParam("hobby") String[] hobby) {
        System.out.println(username);
        System.out.println(password);
        for (String h : hobby) {
            System.out.println(h);
        }
        return "success";
    }

    @RequestMapping("/testPOJO")
    public String testPOJO(User user, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        System.out.println(user);
        System.out.println("张三");
        return "success";
    }
}
