package com.shirongbao.bank.service.impl;

import com.shirongbao.bank.dao.AccountDao;
import com.shirongbao.bank.pojo.Account;
import com.shirongbao.bank.service.AccountService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("accountService2")
public class AccountServiceImpl2 implements AccountService {

    @Resource(name = "accountDao")
    private AccountDao accountDao;

    @Override
    public void transfer(String fromAccount, String toAccount, double money) {

    }

    @Override
    @Transactional(noRollbackFor = NullPointerException.class, propagation = Propagation.REQUIRES_NEW)
    public void save(Account act) {
        accountDao.insert(act);
//        String s = null;
//        s.toString();
    }
}
