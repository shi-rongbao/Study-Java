package com.shiongbao.cloud.controller;

import com.shirongbao.cloud.apis.PayFeignApi;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author ShiRongbao
 * @create 2024/3/14 22:33
 * @description:
 */
@RestController
public class OrderCircuitController {

    @Resource
    private PayFeignApi payFeignApi;

    @GetMapping("/feign/pay/circuit/{id}")
    @CircuitBreaker(name = "cloud-payment-service", fallbackMethod = "myCircuitFallback")
    // 这里的fallbackMethod 指向服务降级的方法
    public String myCircuitBreaker(@PathVariable("id") Integer id) {
        return payFeignApi.myCircuit(id);
    }

    /**
     * 服务降级的方法
     * 发生异常后，直接服务降级
     *
     * @param t 异常对象
     * @return 返回
     */
    public String myCircuitFallback(Integer id, Throwable t) {
        return "myCircuitFallback, 系统繁忙，请稍后再试";
    }


    /*@GetMapping("/feign/pay/bulkhead/{id}")
    @Bulkhead(name = "cloud-payment-service", fallbackMethod = "myBulkheadFallback", type = Bulkhead.Type.SEMAPHORE)
    public String myBulkhead(@PathVariable("id") Integer id) {
        return payFeignApi.myBulkhead(id);
    }

    public String myBulkheadFallback(Integer id, Throwable t){
        return "myBulkheadFallback, 隔板超出最大数量限制，系统繁忙，请稍后再试";
    }*/

    @GetMapping("/feign/pay/bulkhead/{id}")
    @Bulkhead(name = "cloud-payment-service", fallbackMethod = "myBulkheadPoolFallback", type = Bulkhead.Type.THREADPOOL)
    public CompletableFuture<String> myBulkheadTHREADPOOL(@PathVariable("id") Integer id) {
        System.out.println(Thread.currentThread().getName() + "\t" + "===== 开始进入");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "\t" + "===== 准备离开");
        return CompletableFuture.supplyAsync(() -> payFeignApi.myBulkhead(id) + "\t" + "Bulkhead.Type.THREADPOOL");
    }

    public CompletableFuture<String> myBulkheadPoolFallback(Integer id, Throwable t) {
        return CompletableFuture.supplyAsync(() -> "Bulkhead.Type.THREADPOOL,系统繁忙，请稍后重试");
    }


    @GetMapping(value = "/feign/pay/ratelimit/{id}")
    @RateLimiter(name = "cloud-payment-service", fallbackMethod = "myRatelimitFallback")
    public String myBulkhead(@PathVariable("id") Integer id) {
        return payFeignApi.myRatelimit(id);
    }

    public String myRatelimitFallback(Integer id, Throwable t) {
        return "你被限流了，禁止访问/(ㄒoㄒ)/~~";
    }
}
