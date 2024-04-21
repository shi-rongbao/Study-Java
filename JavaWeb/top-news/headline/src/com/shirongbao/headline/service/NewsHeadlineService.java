package com.shirongbao.headline.service;

import com.shirongbao.headline.pojo.NewsHeadline;
import com.shirongbao.headline.pojo.vo.HeadlineDetailVo;
import com.shirongbao.headline.pojo.vo.HeadlineQueryVo;

import java.util.Map;

public interface NewsHeadlineService {
    /**
     * 根据用户传递的信息进行分页查询
     * @param headlineQueryVo 用户传来的信息
     * @return 返回一个map集合,里面存储着信息
     */
    Map findPage(HeadlineQueryVo headlineQueryVo);

    /**
     * 根据用户点击的查看详情所对应的新闻的hid,查询新闻详情返回给客户端
     * @param hid 被点击新闻的hid
     * @return 返回HeadlineDetailVo对象
     */
    HeadlineDetailVo findHeadLineDetail(int hid);

    int addNewsHeadline(NewsHeadline newsHeadline);

    /**
     * 根据用户点击的修改的新闻对应的hid,返回NewsHeadline对象
     * @param hid 用户点击的修改的新闻对应的hid
     * @return 返回NewsHeadline对象
     */
    NewsHeadline findByHid(int hid);

    int update(NewsHeadline headline);

    int removeByHid(int hid);
}
