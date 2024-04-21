package com.study.minterface2;

import java.util.Objects;

public class Test {
    public static void main(String[] args) {
        PingPongPlayer ppp = new PingPongPlayer("马龙",23);
        System.out.println(ppp.getName()+ppp.getAge());
        ppp.play();
        ppp.speakEnglish();

    }
}
