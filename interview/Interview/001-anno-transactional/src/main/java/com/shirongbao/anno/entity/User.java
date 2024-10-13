package com.shirongbao.anno.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author ShiRongbao
 * @create 2024/10/8
 * @description:
 */
@Data
public class User {

    private Long id;

    private String username;

    private String password;

    private String email;

    private Date createdAt;

    private Date updatedAt;
}
