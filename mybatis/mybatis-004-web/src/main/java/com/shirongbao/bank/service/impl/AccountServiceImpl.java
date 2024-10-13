package com.shirongbao.bank.service.impl;

import com.shirongbao.bank.dao.AccountDao;
import com.shirongbao.bank.exception.MoneyNotEnoughException;
import com.shirongbao.bank.exception.TransferException;
import com.shirongbao.bank.pojo.Account;
import com.shirongbao.bank.service.AccountService;
import com.shirongbao.bank.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

public class AccountServiceImpl implements AccountService {

    // 自己创建AccountDao的实例类
    // private AccountDao accountDao = new AccountDaoImpl();

    // 自己封装的GenerateDaoProxy工具类
    // private AccountDao accountDao = (AccountDao) GenerateDaoProxy.generate(SqlSessionUtil.openSession(), AccountDao.class);
    private AccountDao accountDao = SqlSessionUtil.openSession().getMapper(AccountDao.class);

    @Override
    public void transfer(String fromActno, String toActno, double money) throws MoneyNotEnoughException, TransferException {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        // 判断转出账户的余额是否充足(select)
        Account fromAccount = accountDao.selectByActno(fromActno);
        if (fromAccount.getBalance() < money) {
            // 如果转出账户余额不足,提示用户
            throw new MoneyNotEnoughException("您的余额不足");
        }
        // 如果转出账户余额充足,更新转出账户余额(update)
        Account toAccount = accountDao.selectByActno(toActno);
        // 修改内存中的Account余额
        fromAccount.setBalance(fromAccount.getBalance() - money);
        toAccount.setBalance(toAccount.getBalance() + money);
        // 更新转入账户余额(update)
        int rows = accountDao.updateAccount(fromAccount);

//        // 模拟异常
//        String s = null;
//        s.toUpperCase();

        rows += accountDao.updateAccount(toAccount);
        if (rows != 2) {
            throw new TransferException("转账出现异常!");
        }
        // 提交
        sqlSession.commit();
        // 关闭
        SqlSessionUtil.close(sqlSession);
    }
}
