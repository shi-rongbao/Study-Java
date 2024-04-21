package com.shirongbao.bank.service.impl;

import com.shirongbao.bank.dao.AccountDao;
import com.shirongbao.bank.pojo.Account;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service("i1")
public class IsolationService1 {
    @Resource(name = "accountDao")
    private AccountDao accountDao;

    //@Transactional(isolation = Isolation.READ_UNCOMMITTED)  // 开启事务隔离级别读未提交
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)  // 开启事务隔离级别读提交  解决脏读问题
    public void getByActno(String act) {
        Account account = accountDao.selectByAccount(act);
        System.out.println("查询到的账户是" + account);
    }
}
