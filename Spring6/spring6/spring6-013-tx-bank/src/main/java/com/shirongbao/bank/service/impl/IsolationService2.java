package com.shirongbao.bank.service.impl;

import com.shirongbao.bank.dao.AccountDao;
import com.shirongbao.bank.pojo.Account;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("i2")
public class IsolationService2 {
    @Resource(name = "accountDao")
    private AccountDao accountDao;

    @Transactional(timeout = 10)  // 超时时间设置为10秒,所有DML语句如果没执行完那么回滚
    public void save(Account act) {
        accountDao.insert(act);
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(3 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
