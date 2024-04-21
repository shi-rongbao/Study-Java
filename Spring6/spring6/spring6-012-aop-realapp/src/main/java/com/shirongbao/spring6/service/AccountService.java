package com.shirongbao.spring6.service;

import org.springframework.stereotype.Service;

@Service
public class AccountService {
    public void transfer(){
        System.out.println("银行账户正在完成转账");
    }

    public void withdraw(){
        System.out.println("正在取款,请稍后....");
        String str = null;
        System.out.println(str.toString());
    }
}
