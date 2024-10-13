package com.shirongbao.schedule.service.impl;

import com.shirongbao.schedule.dao.SysUserDao;
import com.shirongbao.schedule.dao.impl.SysUserDaoImpl;
import com.shirongbao.schedule.pojo.SysUser;
import com.shirongbao.schedule.service.SysUserService;
import com.shirongbao.schedule.util.MD5Util;

public class SysUserServiceImpl implements SysUserService {
    private SysUserDao userDao = new SysUserDaoImpl();
    @Override
    public int regist(SysUser sysUser) {
        // 首先将用户的明文密码转换为密文密码
        sysUser.setUserPwd(MD5Util.encrypt(sysUser.getUserPwd()));
        // 调用Dao 层的方法,将sysUser信息存入数据库
        return userDao.addSysUser(sysUser);
    }

    @Override
    public SysUser findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
