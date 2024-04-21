package com.shirongbao.bank.service.impl;

import com.shirongbao.bank.dao.AccountDao;
import com.shirongbao.bank.pojo.Account;
import com.shirongbao.bank.service.AccountService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("accountService")
//@Transactional
public class AccountServiceImpl implements AccountService {
    @Resource(name = "accountDao")
    private AccountDao accountDao;

    @Override
    @Transactional
    public void transfer(String fromAccount, String toAccount, double money) {
        Account fromAct = accountDao.selectByAccount(fromAccount);
        if (fromAct.getBalance() < money) {
            throw new RuntimeException("余额不足");
        }
        Account toAct = accountDao.selectByAccount(toAccount);
        fromAct.setBalance(fromAct.getBalance() - money);
        toAct.setBalance(toAct.getBalance() + money);

        int rows = accountDao.update(fromAct);

        // 模拟异常
        String s = null;
        s.toUpperCase();

        rows += accountDao.update(toAct);
        if (rows != 2) {
            throw new RuntimeException("转账失败,请联系银行");
        }
    }

    @Resource(name = "accountService2")
    private AccountService accountService2;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(Account act) {
        accountDao.insert(act);
        Account act2 = new Account("act-004", 10000.0);
        accountService2.save(act2);

//        try {
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
