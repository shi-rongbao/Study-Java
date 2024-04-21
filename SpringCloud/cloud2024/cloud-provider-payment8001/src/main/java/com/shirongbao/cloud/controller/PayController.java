package com.shirongbao.cloud.controller;

import com.shirongbao.cloud.entities.Pay;
import com.shirongbao.cloud.entities.PayDTO;
import com.shirongbao.cloud.resp.ResultData;
import com.shirongbao.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author ShiRongbao
 * @create 2024/3/10 15:20
 * @description:
 */
@RestController
@Slf4j
@Tag(name = "支付微服务模块", description = "支付CRUD")
public class PayController {

    @Resource
    private PayService payService;

    @PostMapping("/pay/add")
    @Operation(summary = "新增", description = "新增支付流水方法，JSON串做参数")
    public ResultData<String> addPay(@RequestBody Pay pay) {
        log.info("插入记录：{}", pay);
        int count = payService.add(pay);
        return ResultData.success("成功插入记录，返回值：" + count);
    }

    @DeleteMapping("/pay/del/{id}")
    @Operation(summary = "根据主键删除流水记录", description = "删除支付流水方法，路径参数id")
    public ResultData<Integer> deletePay(@PathVariable("id") Integer id) {
        log.info("删除记录：{}", id);
        return ResultData.success(payService.delete(id));
    }

    @PutMapping("/pay/update")
    @Operation(summary = "根据主键更新", description = "根据传入的PayDTO JSON对象更新支付流水，重要的是主键id")
    public ResultData<String> updatePay(@RequestBody PayDTO payDTO) {
        log.info("根据主键修改记录, 这个JSON一定要包含id：{}", payDTO);
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO, pay);
        int count = payService.update(pay);
        return ResultData.success("修改成功，返回值：" + count);
    }

    @GetMapping("/pay/get/{id}")
    @Operation(summary = "根据主键查询", description = "根据路径参数id查询支付流水对象")
    public ResultData<Pay> getById(@PathVariable("id") Integer id) {
        log.info("查询记录：{}", id);
        /*// 模拟异常
        String a = null;
        a.toUpperCase();*/

        /*// 模拟异常，测试feign的默认超时时间
        try {TimeUnit.SECONDS.sleep(5);} catch(InterruptedException e) {e.printStackTrace();}*/

        return ResultData.success(payService.getById(id));
    }

    @GetMapping("/pay/get")
    @Operation(summary = "查询全部", description = "查询全部支付流水")
    public ResultData<List<Pay>> getAll() {
        log.info("查询全部");
        return ResultData.success(payService.getAll());
    }


    /*
    测试从consul中获取信息
     */

    @Value("${server.port}")
    private String port;

    @GetMapping("/pay/get/info")
    public String getInfoByConsul(@Value("${shirongbao.info}") String shirongbaoInfo){
        return "shirongbaoInfo: " + shirongbaoInfo + "\t" + port;
    }
}
