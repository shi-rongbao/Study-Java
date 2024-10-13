package com.shirongbao.headline.service.impl;

import com.shirongbao.headline.dao.NewsTypeDao;
import com.shirongbao.headline.dao.impl.NewsTypeDaoImpl;
import com.shirongbao.headline.pojo.NewsType;
import com.shirongbao.headline.service.NewsTypeService;

import java.util.List;

public class NewsTypeServiceImpl implements NewsTypeService {
    private NewsTypeDao typeDao = new NewsTypeDaoImpl();
    @Override
    public List<NewsType> findAll() {
        return typeDao.findAll();
    }
}
