package com.shirongbao.schedule.test;

import com.shirongbao.schedule.dao.BaseDao;
import com.shirongbao.schedule.pojo.SysUser;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class TestBaseDao {
    private static BaseDao baseDao;

    @BeforeClass
    public static void initBaseDao() {
        baseDao = new BaseDao();
    }

    @Test
    public void testBaseQueryObject() {
        // 查询用户数量, baseQueryObject  查询结果集是单行单列的 一个值的数据的方法
        String sql = "SELECT count(*) FROM sys_user";
        Long count = baseDao.baseQueryObject(Long.class, sql);
        System.out.println(count);  // 2
    }

    @Test
    public void testBaseQuery() {
        String sql = "SELECT uid, username, user_pwd as userPwd FROM sys_user";
        List<SysUser> sysUserList = baseDao.baseQuery(SysUser.class, sql);
        sysUserList.forEach(System.out::println);
    }

    @Test
    public void testBaseUpdate(){
        String sql = "INSERT INTO sys_schedule VALUES(DEFAULT, ?, ?, ?)";
        int rows = baseDao.baseUpdate(sql, 1, "fuck you", 0);
        System.out.println(rows);
    }
}
