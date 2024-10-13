package com.shirongbao.boot.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author ShiRongbao
 * @create 2024/2/10 11:40
 * @description:
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Child {
    private String name;
    private Integer age;
    private Date birthDay;
    private List<String> text;
}
