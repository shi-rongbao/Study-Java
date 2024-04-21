package com.shirongbao.cloud.apis;

import com.shirongbao.cloud.entities.PayDTO;
import com.shirongbao.cloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * @author ShiRongbao
 * @create 2024/3/13 22:02
 * @description:
 */
//@FeignClient(value = "cloud-payment-service")
@FeignClient(value = "cloud-gateway")
public interface PayFeignApi {

    /**
     * 插入一条流水记录
     * @param payDTO 要插入的数据
     * @return 返回
     */
    @PostMapping("/pay/add")
    ResultData addPay(@RequestBody PayDTO payDTO);

    /**
     * 根据主键删除流水记录
     * @param id 主键
     * @return 返回影响数据库行数
     */
    @DeleteMapping("/pay/del/{id}")
    ResultData deletePay(@PathVariable("id") Integer id);


    /**
     * 根据传入的payDTO更新流水记录
     * @param payDTO 要更新的数据
     * @return 返回
     */
    @PutMapping("/pay/update")
    ResultData updatePay(@RequestBody PayDTO payDTO);

    /**
     * 根据主键查询流水记录
     * @param id 主键id
     * @return 返回查到的数据
     */
    @GetMapping("/pay/get/{id}")
    ResultData getById(@PathVariable("id") Integer id);


    /**
     * 查询所有支付流水
     * @return 返回查到的数据
     */
    @GetMapping("/pay/get")
    ResultData getAll();


    /**
     * 从consul中获取信息
     * @return 返回拿到的信息
     */
    @GetMapping("/pay/get/info")
    String getInfoByConsul();

    /**
     * 调用服务熔断降级测试方法
     * @param id id
     * @return 返回
     */
    @GetMapping("/pay/circuit/{id}")
    String myCircuit(@PathVariable("id") Integer id);

    /**
     * 隔板测试方法
     * @param id id
     * @return 返回
     */
    @GetMapping("/pay/bulkhead/{id}")
    String myBulkhead(@PathVariable("id") Integer id);

    /**
     * 限流测试方法
     * @param id id
     * @return 返回
     */
    @GetMapping("/pay/retelimit/{id}")
    String myRatelimit(@PathVariable("id") Integer id);


    /**
     * Micrometer(Sleuth)进行链路监控的例子
     * @param id id
     * @return 返回
     */
    @GetMapping(value = "/pay/micrometer/{id}")
    String myMicrometer(@PathVariable("id") Integer id);

    /**
     * GateWay进行网关测试案例01
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/gateway/get/{id}")
    ResultData getByIdGateWay(@PathVariable("id") Integer id);

    /**
     * GateWay进行网关测试案例02
     * @return
     */
    @GetMapping(value = "/pay/gateway/info")
    ResultData<String> getGatewayInfo();
}
