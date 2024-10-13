package com.shirongbao.web.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ShiRongbao
 * @create 2024/2/20 21:21
 * @description:
 */
@ControllerAdvice  // 该注解表示这个雷集中处理所有@Controller 发生的错误
public class GlobalExceptionHandler {

    // 该类下的@ExceptionHandler注解标识的错误处理方法可以管理所有类
    /*@ResponseBody
    @ExceptionHandler*/
    public String handleException(Exception e){
        return "Something Wrong 这是统一处理, 原因:" + e.getMessage();
    }
}
