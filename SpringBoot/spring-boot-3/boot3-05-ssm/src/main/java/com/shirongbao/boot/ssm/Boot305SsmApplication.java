package com.shirongbao.boot.ssm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.shirongbao.boot.ssm.mapper")  // 告诉mybatis,要扫描哪个包下的所有接口
@SpringBootApplication
public class Boot305SsmApplication {

    public static void main(String[] args) {
        SpringApplication.run(Boot305SsmApplication.class, args);
    }

}
