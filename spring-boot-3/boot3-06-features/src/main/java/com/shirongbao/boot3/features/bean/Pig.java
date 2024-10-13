package com.shirongbao.boot3.features.bean;

import lombok.Data;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author ShiRongbao
 * @create 2024/2/10 10:44
 * @description:
 */
// @ConfigurationProperties(prefix = "pig")
@Data
@Component
@Profile("prod")
public class Pig {
    private Long id;
    private String name;
    private Integer age;

}
