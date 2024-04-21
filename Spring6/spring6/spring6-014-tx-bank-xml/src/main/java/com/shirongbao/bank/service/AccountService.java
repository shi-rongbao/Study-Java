package com.shirongbao.bank.service;

import com.shirongbao.bank.pojo.Account;

public interface AccountService {
    /**
     * 转账业务方法
     * @param fromAccount     从哪个账号转出
     * @param toAccount       转入哪个账号
     * @param money         转账的金额
     */
    void transfer(String fromAccount, String toAccount, double money);
}
