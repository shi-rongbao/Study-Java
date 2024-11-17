package com.shirongbao.example;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static int solution(int[] cards) {
        Map<Integer, Integer> map  = new HashMap<>();
        for (int card : cards) {
            if (map.containsKey(card)) {
                map.put(card, 2);
            } else{
                map.put(card, 1);
            }
        }

        return 1;
    }

    public static void main(String[] args) {
        // Add your test cases here

        System.out.println(solution(new int[]{1, 1, 2, 2, 3, 3, 4, 5, 5}) == 4);
    }
}
