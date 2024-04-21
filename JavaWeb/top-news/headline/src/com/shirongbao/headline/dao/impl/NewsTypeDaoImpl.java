package com.shirongbao.headline.dao.impl;

import com.shirongbao.headline.dao.BaseDao;
import com.shirongbao.headline.dao.NewsTypeDao;
import com.shirongbao.headline.pojo.NewsType;

import java.util.List;

public class NewsTypeDaoImpl extends BaseDao implements NewsTypeDao {
    @Override
    public List<NewsType> findAll() {
        String sql = "SELECT tid, tname FROM news_type";

        return baseQuery(NewsType.class, sql);
    }
}
