package com.shirongbao.logging.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ShiRongbao
 * @create 2024/2/11 23:25
 * @description:
 */
@RestController
@Slf4j
public class HelloController {
    // Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/hello")
    public String hello(String a, String b) {
        // log.info("\nfuck you, this method invoked 你好");
        // logger.info("\nfuck you, this method invoked 你好");
        log.trace("trace 日志...");
        log.debug("debug 日志...");
        // springboot日志默认级别是INFO
        log.info("\ninfo 日志... 参数a: {} 参数b: {}", a, b);
        log.warn("warn 日志...");
        log.error("error 日志...");
        return "Hello SpringBoot3";
    }

}
