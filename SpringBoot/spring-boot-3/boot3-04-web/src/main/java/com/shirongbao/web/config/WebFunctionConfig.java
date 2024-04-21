package com.shirongbao.web.config;

import com.shirongbao.web.biz.UserBizHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

/**
 * @author ShiRongbao
 * @create 2024/2/21 22:47
 * @description:
 */
@Configuration
public class WebFunctionConfig {



    /**
     * 函数式Web:
     * 1.给容器中放一个Bean:类型是 RouterFunction<ServerResponse>
     * <p>
     * 核心的四大对象:
     * 1. RouterFunction:      定义路由信息.发什么请求,谁来处理
     * 2. RequestPredicate:    定义请求:请求谓语(请求方式,GET, POST),请求参数
     * 3. ServerRequest:       封装请求完整数据
     * 4. ServerResponse:      封装响应完整数据
     *
     * @return
     */

    @Bean
    public RouterFunction<ServerResponse> userRoute(@Autowired UserBizHandler userBizHandler) {
        return RouterFunctions.route()
                .GET("/user/{id}", userBizHandler::getUser)
                .GET("/users", userBizHandler::getUsers)
                .POST("/user", RequestPredicates.accept(MediaType.APPLICATION_JSON), userBizHandler::saveUser)
                .PUT("/user/{id}", RequestPredicates.accept(MediaType.APPLICATION_JSON), userBizHandler::updateUser)
                .DELETE("/user/{id}", userBizHandler::deleteUser)
                .build();
    }

}
