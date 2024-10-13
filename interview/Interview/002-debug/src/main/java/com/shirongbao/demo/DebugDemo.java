package com.shirongbao.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShiRongbao
 * @create 2024/10/8
 * @description:
 */
public class DebugDemo {
    public static void main(String[] args) {
        // List<Integer> list = Stream.of(1, 2, 3, 4, 5, 6).filter(f -> f > 3).map(m -> m * 2).collect(Collectors.toList());
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 17; i++) {
            list.add(i);
        }
    }
}
