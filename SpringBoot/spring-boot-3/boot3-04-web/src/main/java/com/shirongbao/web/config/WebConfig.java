package com.shirongbao.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ShiRongbao
 * @create 2024/2/20 22:33
 * @description:
 */

// 全面接管SpringMVC
/*@EnableWebMvc  // 禁用mvc的所有默认功能
@Configuration*/
public class WebConfig implements WebMvcConfigurer {

}
