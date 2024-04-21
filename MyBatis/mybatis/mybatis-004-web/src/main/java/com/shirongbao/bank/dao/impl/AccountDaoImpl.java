package com.shirongbao.bank.dao.impl;

import com.shirongbao.bank.dao.AccountDao;
import com.shirongbao.bank.pojo.Account;
import com.shirongbao.bank.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

public class AccountDaoImpl implements AccountDao {
    @Override
    public Account selectByActno(String actno) {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        return (Account) sqlSession.selectOne("fuck.selectByActno", actno);
    }

    @Override
    public int updateAccount(Account act) {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        return sqlSession.update("fuck.updateByActno", act);
    }
}
