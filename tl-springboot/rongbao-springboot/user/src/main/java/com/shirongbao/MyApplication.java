package com.shirongbao;

import org.springframework.context.annotation.Import;

/**
 * @author ShiRongbao
 * @create 2024/10/4
 * @description:
 */
@RongbaoSpringbootApplication
@Import(MyImportSelector.class)
public class MyApplication {
    public static void main(String[] args) {
        RongbaoSpringApplication.run(MyApplication.class);
    }

}
