package com.shirongbao.cloud;

import java.time.ZonedDateTime;

/**
 * @author ShiRongbao
 * @create 2024/3/17 12:41
 * @description:
 */
public class Main {
    public static void main(String[] args) {
        ZonedDateTime now = ZonedDateTime.now();  // 默认时区
        System.out.println(now);
    }
}
