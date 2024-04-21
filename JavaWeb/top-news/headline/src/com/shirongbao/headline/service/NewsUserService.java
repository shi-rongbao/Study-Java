package com.shirongbao.headline.service;

import com.shirongbao.headline.pojo.NewsUser;

public interface NewsUserService {
    /**
     * 根据用户登录时传入的账号查询该用户的信息
     * @param username 用户登录时传入的账号
     * @return 返回该用户名对应的NewsUser对象 找不到返回null
     */
    NewsUser findByUsername(String username);

    /**
     * 用于第二次请求,同时通过userId判断用户的token是否非空且有效,并最后响应给客户端
     * @param userId 用户的userId
     * @return 返回该userId对应的NewsUser对象 找不到返回null
     */
    NewsUser findByUid(Integer userId);

    /**
     * 根据客户端传入的注册信息写入数据库
     * @param registUser 用户注册提交的用户信息
     * @return 返回影响数据库的行数
     */
    Integer registUser(NewsUser registUser);
}
