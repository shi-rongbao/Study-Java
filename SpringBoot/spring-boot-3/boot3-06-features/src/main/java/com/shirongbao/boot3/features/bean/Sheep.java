package com.shirongbao.boot3.features.bean;

import lombok.Data;
import org.springframework.context.annotation.Profile;

/**
 * @author ShiRongbao
 * @create 2024/2/10 10:53
 * @description:
 */
@Data
@Profile("default")
public class Sheep {
    private Long id;
    private String name;
    private Integer age;
}

