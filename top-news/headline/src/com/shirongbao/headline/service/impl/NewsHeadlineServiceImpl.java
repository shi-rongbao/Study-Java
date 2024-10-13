package com.shirongbao.headline.service.impl;

import com.shirongbao.headline.dao.NewsHeadlineDao;
import com.shirongbao.headline.dao.impl.NewsHeadlineDaoImpl;
import com.shirongbao.headline.pojo.NewsHeadline;
import com.shirongbao.headline.pojo.vo.HeadlineDetailVo;
import com.shirongbao.headline.pojo.vo.HeadlinePageVo;
import com.shirongbao.headline.pojo.vo.HeadlineQueryVo;
import com.shirongbao.headline.service.NewsHeadlineService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsHeadlineServiceImpl implements NewsHeadlineService {
    private NewsHeadlineDao headlineDao = new NewsHeadlineDaoImpl();

    @Override
    public Map findPage(HeadlineQueryVo headlineQueryVo) {
        int pageNum = headlineQueryVo.getPageNum();
        int pageSize = headlineQueryVo.getPageSize();
        List<HeadlinePageVo> pageData = headlineDao.findPageList(headlineQueryVo);
        int totalSize = headlineDao.findPageCount(headlineQueryVo);
        int totalPage = (totalSize % pageSize) == 0 ? (totalSize % pageSize) : (totalSize % pageSize) + 1;
        Map pageInfo = new HashMap();
        pageInfo.put("pageNum", pageNum);
        pageInfo.put("pageSize", pageSize);
        pageInfo.put("totalSize", totalSize);
        pageInfo.put("totalPage", totalPage);
        pageInfo.put("pageData", pageData);
        return pageInfo;
    }

    @Override
    public HeadlineDetailVo findHeadLineDetail(int hid) {
        // 修改头条的浏览量 + 1
        headlineDao.incrPageViews(hid);
        // 查看头条的详情
        return headlineDao.findHealineDetail(hid);
    }

    @Override
    public int addNewsHeadline(NewsHeadline newsHeadline) {
        return headlineDao.addNewsHeadline(newsHeadline);
    }

    @Override
    public NewsHeadline findByHid(int hid) {
        return headlineDao.findByHid(hid);
    }

    @Override
    public int update(NewsHeadline headline) {
        return headlineDao.update(headline);
    }

    @Override
    public int removeByHid(int hid) {
        return headlineDao.removeByHid(hid);
    }
}
