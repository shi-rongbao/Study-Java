package com.shirongbao.schedule.dao.impl;

import com.shirongbao.schedule.dao.BaseDao;
import com.shirongbao.schedule.dao.SysUserDao;
import com.shirongbao.schedule.pojo.SysUser;

import java.util.List;

public class SysUserDaoImpl extends BaseDao implements SysUserDao {

    @Override
    public int addSysUser(SysUser sysUser) {
        String sql = "INSERT INTO sys_user VALUES(DEFAULT, ?, ?)";
        return baseUpdate(sql, sysUser.getUsername(), sysUser.getUserPwd());
    }

    @Override
    public SysUser findByUsername(String username) {
        String sql = "SELECT uid, username, user_pwd userPwd FROM sys_user WHERE username = ?";
        List<SysUser> userList = baseQuery(SysUser.class, sql, username);
        return userList != null && userList.size() > 0 ? userList.get(0) : null;
    }
}
