package com.shiongbao.cloud.controller;

import com.shirongbao.cloud.apis.PayFeignApi;
import com.shirongbao.cloud.entities.PayDTO;
import com.shirongbao.cloud.resp.ResultData;
import com.shirongbao.cloud.resp.ReturnCodeEnum;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @author ShiRongbao
 * @create 2024/3/13 22:15
 * @description: 使用OpenFeign
 */
@RestController
public class OrderController {
    /*
        使用Feign进行服务接口调用与负载均衡的步骤：
            第一步：    在公共模块编写Api接口，编写对应的服务接口  要在当前接口使用 @FeignClient("cloud-payment-service") 标记要调用服务的consul中注册的name
            第二步：    在当前要调用服务的模块的主启动类上标注 @EnableFeignClients 注解，表示启用feign客户端
            第三步：    编写当前模块中的controller控制器方法，面向接口编程，使用公共模块中的Api接口访问服务

        访问过程：
            用户访问客户端模块中的控制器方法 ——> feign使用 标注了 @FeignClient 的接口 扫描对应的控制器方法，再根据控制器方法对应的注解生成对应的HTTP请求来调用服务模块中的控制器方法  ——> 最后执行服务端的控制器方法
     */

    @Resource
    private PayFeignApi payFeignApi;

    /**
     * 使用feign调用PayFeignApi来调用服务
     *
     * @param payDTO 要添加的数据
     * @return 返回数据
     */
    @PostMapping("/feign/pay/add")
    public ResultData addOrder(@RequestBody PayDTO payDTO) {
        return payFeignApi.addPay(payDTO);
    }


    /**
     * 使用feign调用PayFeignApi来调用服务
     *
     * @param id 要删除流水的主键id
     * @return 返回数据
     */
    @DeleteMapping("/feign/pay/{id}")
    public ResultData deletePay(@PathVariable("id") Integer id) {
        return payFeignApi.deletePay(id);
    }

    /**
     * 使用feign调用PayFeignApi来调用服务
     *
     * @param payDTO 要更新的数据
     * @return 返回数据
     */
    @PutMapping("/feign/pay/update")
    public ResultData updatePay(@RequestBody PayDTO payDTO) {
        return payFeignApi.updatePay(payDTO);
    }


    /**
     * 根据主键查询流水记录
     *
     * @param id 主键id
     * @return 返回数据
     */
    @GetMapping("/feign/pay/get/{id}")
    public ResultData getById(@PathVariable("id") Integer id) {
        ResultData resultData = null;
        try {
            // System.out.println("调用开始========" + DateUtil.now());
            resultData = payFeignApi.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
            // System.out.println("调用结束=======" + DateUtil.now());
            ResultData.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());
        }
        return resultData;
    }


    /**
     * 查询所有支付流水
     *
     * @return 返回查询到的数据
     */
    @GetMapping("/feign/pay/get")
    public ResultData getAll() {
        return payFeignApi.getAll();
    }


    /**
     * 从consul中获取信息
     *
     * @return 返回数据
     */
    @GetMapping("/feign/pay/get/info")
    public String getInfoByConsul() {
        return payFeignApi.getInfoByConsul();
    }

}
