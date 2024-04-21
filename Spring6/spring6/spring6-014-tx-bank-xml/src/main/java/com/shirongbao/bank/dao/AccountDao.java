package com.shirongbao.bank.dao;

import com.shirongbao.bank.pojo.Account;

public interface AccountDao {

    /**
     * 根据账号查询账户信息
     * @param account 要查询信息的账号
     * @return 账号存在返回该账号,不存在返回null
     */
    Account selectByAccount(String account);

    /**
     * 更新账户信息
     * @param account 要更新的账户的账号
     * @return 返回影响数据库的行数
     */
    int update(Account account);
}
