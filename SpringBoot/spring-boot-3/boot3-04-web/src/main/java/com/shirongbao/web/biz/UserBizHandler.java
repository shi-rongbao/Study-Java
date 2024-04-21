package com.shirongbao.web.biz;

import com.shirongbao.web.pojo.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.Arrays;
import java.util.List;

/**
 * @author ShiRongbao
 * @create 2024/2/21 23:33
 * @description:
 */
@Slf4j
@Component
public class UserBizHandler {

    public ServerResponse getUser(ServerRequest request) {
        String id = request.pathVariable("id");
        log.info("查询{}号用户信息完成", id);
        // 业务处理
        Person person = new Person(1L, "张三", "zhangsan@qq.com", 23, "admin");
        // 响应构造
        return ServerResponse.ok().body(person);
    }

    public ServerResponse getUsers(ServerRequest serverRequest) {
        log.info("查询所有用户信息完成");
        List<Person> list = Arrays.asList(new Person(1L, "张三", "zhangsan@qq.com", 23, "admin"),
                new Person(2L, "李四", "lisi@qq.com", 24, "user"));
        return ServerResponse.ok().body(list);  // 凡是body中的对象,就是以前@ResponseBody原理.利用HttpMessageConverter写出为JSON
    }

    public ServerResponse saveUser(ServerRequest serverRequest) {
        log.info("保存信息完成");
        return ServerResponse.ok().build();
    }

    public ServerResponse updateUser(ServerRequest serverRequest) {
        log.info("用户信息更新完成");
        return ServerResponse.ok().build();
    }

    public ServerResponse deleteUser(ServerRequest serverRequest) {
        log.info("删除用户信息完成");
        return ServerResponse.ok().build();
    }
}
