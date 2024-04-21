package com.shirongbao.headline.dao.impl;

import com.shirongbao.headline.dao.BaseDao;
import com.shirongbao.headline.dao.NewsUserDao;
import com.shirongbao.headline.pojo.NewsUser;

import java.util.List;

public class NewsUserDaoImpl extends BaseDao implements NewsUserDao {
    @Override
    public NewsUser findByUsername(String username) {
        String sql = """
                SELECT uid, username, user_pwd userPwd, nick_name nickName
                FROM news_user
                WHERE username = ?;
                """;
        List<NewsUser> userList = baseQuery(NewsUser.class, sql, username);
        return userList != null && userList.size() > 0 ? userList.get(0) : null;
    }

    @Override
    public NewsUser findByUid(Integer userId) {
        String sql = """
                SELECT uid, username, user_pwd userPwd, nick_name nickName
                FROM news_user
                WHERE uid = ?;
                """;
        List<NewsUser> userList = baseQuery(NewsUser.class, sql, userId);
        return userList != null && userList.size() > 0 ? userList.get(0) : null;
    }

    @Override
    public Integer insertUser(NewsUser registUser) {
        String sql = """
                INSERT INTO news_user
                VALUES(
                DEFAULT, ?, ?, ?
                );
                """;
        return baseUpdate(sql, registUser.getUsername(), registUser.getUserPwd(), registUser.getNickName());
    }
}
