package com.shirongbao.headline.dao;

import com.shirongbao.headline.pojo.NewsUser;

public interface NewsUserDao {
    /**
     * 根据用户登录时传入的账号查询该用户的信息
     * @param username 用户登录时传入的账号
     * @return 返回该用户名对应的NewsUser对象 找不到返回null
     */
    NewsUser findByUsername(String username);

    NewsUser findByUid(Integer userId);

    Integer insertUser(NewsUser registUser);
}
