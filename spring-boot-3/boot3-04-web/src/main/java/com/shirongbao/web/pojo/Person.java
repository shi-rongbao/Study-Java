package com.shirongbao.web.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ShiRongbao
 * @create 2024/2/21 22:56
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private Long id;
    private String name;
    private String email;
    private Integer age;
    private String role;
}
