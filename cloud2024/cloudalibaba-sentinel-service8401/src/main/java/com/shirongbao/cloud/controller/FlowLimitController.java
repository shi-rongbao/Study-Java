package com.shirongbao.cloud.controller;

import com.shirongbao.cloud.service.FlowLimitService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author ShiRongbao
 * @create 2024/3/20 9:45
 * @description:
 */
@RestController
public class FlowLimitController {

    @Resource
    private FlowLimitService flowLimitService;

    @GetMapping("/testA")
    public String testA() {
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB() {
        return "------testB";
    }

    @GetMapping("/testC")
    public String testC() {
        flowLimitService.common();
        return "------testC";
    }

    @GetMapping("/testD")
    public String testD() {
        flowLimitService.common();
        return "------testD";
    }

    @GetMapping("/testE")
    public String testE() {
        System.out.println(System.currentTimeMillis() + "      testE,排队等待");
        return "------testE";
    }

    /**
     * 新增熔断规则-慢调用比例
     *
     * @return
     */
    @GetMapping("/testF")
    public String testF() {
        //暂停几秒钟线程
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("----测试:新增熔断规则-慢调用比例 ");
        return "------testF 新增熔断规则-慢调用比例";
    }

    /**
     * 新增熔断规则-异常比例
     *
     * @return
     */
    @GetMapping("/testG")
    public String testG() {
        System.out.println("----测试:新增熔断规则-异常比例 ");
        int age = 10 / 0;
        return "------testG,新增熔断规则-异常比例 ";
    }


    /**
     * 新增熔断规则-异常数
     *
     * @return
     */
    @GetMapping("/testH")
    public String testH() {
        System.out.println("----测试:新增熔断规则-异常数 ");
        int age = 10 / 0;
        return "------testH,新增熔断规则-异常数 ";
    }


}
