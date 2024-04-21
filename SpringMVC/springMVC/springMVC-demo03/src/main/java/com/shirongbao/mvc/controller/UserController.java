package com.shirongbao.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * User: ShiRongbao
 * Date: 2024/2/5
 * Time: 22:07
 */
@Controller
public class UserController {

    /**
     * 使用RESTFul模拟用户资源的增删改查
     * /user        GET     查询所有用户信息
     * /user/1      GET     根据用户id查询用户信息
     * /user        POST    添加用户信息
     * /user/1      DELETE  删除用户信息
     * /user        PUT     更新用户信息
     */

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String getAllUser() {
        System.out.println("查询所有用户信息");
        return "success";
    }

    @GetMapping("/user/{id}")
    public String selectById(){
        System.out.println("根据id查询用户信息");
        return "success";
    }

    @PostMapping("/user")
    public String addUser(@RequestParam("username") String username, @RequestParam("password") String password){
        System.out.println("添加用户信息: username:" + username + ", password: " + password);
        return "success";
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public String updateUser(@RequestParam("username") String username, @RequestParam("password") String password){
        System.out.println("修改用户信息: username:" + username + ", password: " + password);
        return "success";
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public String deleteUserById(@PathVariable String id){
        System.out.println("id: " + id + "用户删除成功");
        return "success";
    }
}
