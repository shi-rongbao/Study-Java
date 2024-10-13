package com.shirongbao.cloud.controller;

import com.shirongbao.cloud.entities.PayDTO;
import com.shirongbao.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author ShiRongbao
 * @create 2024/3/11 17:45
 * @description: 调用8001模块方法
 */
@RestController
public class OrderController {

     // public static final String PaymentSrv_URL = "http://localhost:8001";  // 先硬编码写死
     public static final String PaymentSrv_URL = "http://cloud-payment-service";  // 使用consul注册中心的name

    @Resource
    private RestTemplate restTemplate;  // 使用restTemplate实现LoadBalancer负载均衡

    /**
     * 在consumer端口调用provider方法
     * 新增一条流水
     * @param payDTO 添加需要的参数
     * @return 使用restTemplate发送post请求调用provider
     */
    @PostMapping("/consumer/pay/add")
    public ResultData addOrder(@RequestBody PayDTO payDTO) {
        return restTemplate.postForObject(PaymentSrv_URL + "/pay/add", payDTO, ResultData.class);
    }

    /**
     * 在consumer端口调用provider方法
     * 根据主键删除id一条流水
     * @param id 删除时需要的主键
     */
    @DeleteMapping("/consumer/pay/del/{id}")
    public void deleteOrder(@PathVariable("id") Integer id) {
        restTemplate.delete(PaymentSrv_URL + "/pay/del/" + id);
    }

    /**
     * 在consumer端口调用provider方法
     * 更新一条流水记录
     * @param payDTO 更新需要的参数
     */
    @PutMapping("/consumer/pay/update")
    public void updateOrder(@RequestBody PayDTO payDTO){
        restTemplate.put(PaymentSrv_URL + "/pay/update", payDTO, ResultData.class);
    }

    /**
     * 在consumer端口调用provider方法
     * 根据主键id查询对应的流水记录
     * @param id 查询需要的主键id
     * @return 使用restTemplate发送get请求调用provider
     */
    @GetMapping("/consumer/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id){
        return restTemplate.getForObject(PaymentSrv_URL + "/pay/get/" + id, ResultData.class, id);
    }

    /**
     * 在consumer端口调用provider方法
     * 查询所有的流水记录
     * @return 使用restTemplate发送get请求调用provider
     */
    @GetMapping("/consumer/pay/get")
    public ResultData getPayInfoList(){
        return restTemplate.getForObject(PaymentSrv_URL + "/pay/get", ResultData.class);
    }

    /**
     * 在consumer端口调用provider方法
     * 从consul中获取信息
     * @return 使用restTemplate发送get请求调用provider
     */
    @GetMapping("/consumer/pay/get/info")
    public String getInfoByConsul(){
        return restTemplate.getForObject(PaymentSrv_URL + "/pay/get/info", String.class);
    }

}
