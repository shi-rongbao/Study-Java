package com.shirongbao.headline.dao;

import com.shirongbao.headline.pojo.NewsHeadline;
import com.shirongbao.headline.pojo.vo.HeadlineDetailVo;
import com.shirongbao.headline.pojo.vo.HeadlinePageVo;
import com.shirongbao.headline.pojo.vo.HeadlineQueryVo;

import java.util.List;

public interface NewsHeadlineDao {
    List<HeadlinePageVo> findPageList(HeadlineQueryVo headlineQueryVo);

    int findPageCount(HeadlineQueryVo headlineQueryVo);

    int incrPageViews(int hid);

    HeadlineDetailVo findHealineDetail(int hid);

    int addNewsHeadline(NewsHeadline newsHeadline);

    NewsHeadline findByHid(int hid);

    int update(NewsHeadline headline);

    int removeByHid(int hid);
}
