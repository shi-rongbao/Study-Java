package com.shirongbao.cloud.controller;

import cn.hutool.core.util.IdUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author ShiRongbao
 * @create 2024/3/14 22:00
 * @description:
 */
@RestController
public class PayCircuitController {

    /*@GetMapping("/pay/circuit/{id}")
    public String myCircuit(@PathVariable("id") Integer id) {
        if (id == -4) throw new RuntimeException("=========circuit id 不能为负数");

        if (id == 999) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return "hello, circuit! inputId:" + id + "\t" + IdUtil.simpleUUID();
    }*/

    @GetMapping("/pay/bulkhead/{id}")
    public String myBulkhead(@PathVariable("id") Integer id){
        if (id == -4) throw new RuntimeException("========bulkhead id 不能为负数");

        if (id == 999) {
            try{TimeUnit.SECONDS.sleep(5);} catch (Exception e) {e.printStackTrace();}
        }
        return "hello, bulkhead! inputId: " + id + "\t" + IdUtil.simpleUUID();
    }

    @GetMapping("/pay/retelimit/{id}")
    public String myRatelimit(@PathVariable("id") Integer id){
        return "Hello, myRatelimit欢迎来到 inputId: " + id + "\t" + IdUtil.simpleUUID();
    }

}
