package com.shirongbao.headline.service.impl;

import com.shirongbao.headline.dao.NewsUserDao;
import com.shirongbao.headline.dao.impl.NewsUserDaoImpl;
import com.shirongbao.headline.pojo.NewsUser;
import com.shirongbao.headline.service.NewsUserService;
import com.shirongbao.headline.util.MD5Util;

public class NewsUserServiceImpl implements NewsUserService {
    private NewsUserDao userDao = new NewsUserDaoImpl();
    @Override
    public NewsUser findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public NewsUser findByUid(Integer userId) {
        return userDao.findByUid(userId);
    }

    @Override
    public Integer registUser(NewsUser registUser) {
        // 处理增加数据的业务
        // 将明文密码转换为密文密码
        registUser.setUserPwd(MD5Util.encrypt(registUser.getUserPwd()));
        return userDao.insertUser(registUser);
    }
}
