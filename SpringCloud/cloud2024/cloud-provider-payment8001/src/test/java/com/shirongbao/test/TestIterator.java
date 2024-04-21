package com.shirongbao.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

/**
 * @author ShiRongbao
 * @create 2024/3/29 8:53
 * @description:
 */
@SpringBootTest
@SpringBootConfiguration
public class TestIterator {

    @Test
    void testIterator() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("fuck", 1);
        map.put("you", 2);
        map.put("hello", 3);

        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        Set set = new HashSet();

    }
}
