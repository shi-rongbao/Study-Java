package com.shirongbao.bank.dao;

import com.shirongbao.bank.pojo.Account;

/**
 * 账户的DAO对象,负责t_act表中数据的CRUD
 * DAO中的方法就是做CRUD的,所以方法名大部分是: insertXXX deleteXXX updateXXX selectXXX
 */
public interface AccountDao {

    /**
     * 根据账号查询账户信息
     *
     * @param actno 要查询账户的账号
     * @return 如果存在返回被查找的账户对象, 如果不存在返回null
     */
    Account selectByActno(String actno);

    /**
     * 更新账户信息
     * @param act 被更新的账户对象
     * @return 返回1表示更新成功,其他则失败
     */
    int updateAccount(Account act);
}
