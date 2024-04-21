package com.shirongbao.schedule.dao;

import com.shirongbao.schedule.pojo.SysUser;

public interface SysUserDao {
    /**
     * 向数据库中添加一条用户记录的方法
     * @param sysUser 要增加的记录的username和user_pwd字段一SysUser实体类对象的形式接收
     * @return 增加成功返回1 增加失败返回0
     */
    int addSysUser(SysUser sysUser);

    /**
     * 根据用户名获得完整用户信息的方法
     * @param username 要查询的用户名
     * @return 如果找到了返回SysUser对象,如果没找到返回null
     */
    SysUser findByUsername(String username);
}
