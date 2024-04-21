package com.shirongbao.bank.dao.impl;

import com.shirongbao.bank.dao.AccountDao;
import com.shirongbao.bank.pojo.Account;
import jakarta.annotation.Resource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("accountDao")
public class AccountDaoImpl implements AccountDao {

    @Resource(name = "jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public Account selectByAccount(String account) {
        String sql = "SELECT actno, balance FROM t_act WHERE actno = ?;";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Account.class), account);
    }

    @Override
    public int update(Account account) {
        String sql = "UPDATE t_act SET balance = ? WHERE actno = ?;";
        return jdbcTemplate.update(sql, account.getBalance(), account.getActno());
    }

}
