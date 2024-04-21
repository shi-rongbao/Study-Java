package com.study.minterface2;

public class PingPongPlayer extends Person implements SpeakEnglish{

    public PingPongPlayer() {
    }

    public PingPongPlayer(String name, int age) {
        super(name, age);
    }

    @Override
    public void play() {
        System.out.println("学打乒乓球");
    }

    @Override
    public void speakEnglish() {
        System.out.println("说英语");
    }
}
