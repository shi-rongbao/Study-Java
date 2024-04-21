package com.shirongbao.boot3.features.bean;

import lombok.Data;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author ShiRongbao
 * @create 2024/2/9 11:40
 * @description:
 */
@Data
@Component
@Profile("dev")
public class Dog {
    private Integer age;
    private String name;

}
