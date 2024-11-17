package com.shirongbao.todoapp.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ShiRongbao
 * @create 2024/11/17
 * @description:
 */
@Data
public class Todo implements Serializable {

    private String id;
    private String title;
    private boolean completed;

}
