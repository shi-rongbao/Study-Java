package com.study.minterface2;

public class PingPongTeacher extends Person implements SpeakEnglish{

    public PingPongTeacher() {
    }

    public PingPongTeacher(String name, int age) {
        super(name, age);
    }

    @Override
    public void play() {
        System.out.println("教打乒乓球");
    }

    @Override
    public void speakEnglish() {
        System.out.println("说英语");
    }
}
