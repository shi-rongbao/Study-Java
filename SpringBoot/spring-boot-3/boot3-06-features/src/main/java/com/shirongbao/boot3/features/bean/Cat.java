package com.shirongbao.boot3.features.bean;

import jdk.jfr.Period;
import lombok.Data;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author ShiRongbao
 * @create 2024/2/9 11:38
 * @description:
 */
@Data
@Component
@Profile("test")
public class Cat {
    private Integer age;
    private String name;
}
