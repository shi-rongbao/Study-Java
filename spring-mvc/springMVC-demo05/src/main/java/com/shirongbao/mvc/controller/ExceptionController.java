package com.shirongbao.mvc.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by IntelliJ IDEA.
 * User: ShiRongbao
 * Date: 2024/2/8
 * Time: 12:57
 */
@ControllerAdvice()
public class ExceptionController {

    @ExceptionHandler({NullPointerException.class})
    public String testExceptionHandler(Exception exception, Model model) {
        model.addAttribute("exception", exception);
        System.out.println(exception);
        return "nullPointer";
    }

}
