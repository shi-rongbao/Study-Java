package com.shirongbao.headline.dao.impl;

import com.shirongbao.headline.dao.BaseDao;
import com.shirongbao.headline.dao.NewsHeadlineDao;
import com.shirongbao.headline.pojo.NewsHeadline;
import com.shirongbao.headline.pojo.vo.HeadlineDetailVo;
import com.shirongbao.headline.pojo.vo.HeadlinePageVo;
import com.shirongbao.headline.pojo.vo.HeadlineQueryVo;

import java.util.ArrayList;
import java.util.List;

public class NewsHeadlineDaoImpl extends BaseDao implements NewsHeadlineDao {
    @Override
    public List<HeadlinePageVo> findPageList(HeadlineQueryVo headlineQueryVo) {
        List params = new ArrayList();
        String sql = """
                SELECT hid, title, type, page_views pageViews, TIMESTAMPDIFF(HOUR, create_time, NOW()) pastHours, publisher
                FROM news_headline
                WHERE is_deleted = 0
                """;
        if (headlineQueryVo.getType() != 0) {
            sql = sql.concat(" AND type = ? ");
            params.add(headlineQueryVo.getType());
        }
        if (headlineQueryVo.getKeyWords() != null && !headlineQueryVo.getKeyWords().equals("")) {
            sql = sql.concat(" AND title LIKE ? ");
            params.add("%" + headlineQueryVo.getKeyWords() + "%");
        }
        // 根据发布时间与浏览量排序
        sql = sql.concat(" ORDER BY pastHours ASC, page_views DESC ");
        // 分页查询
        sql = sql.concat(" LIMIT ?, ? ");

        params.add((headlineQueryVo.getPageNum() - 1) * headlineQueryVo.getPageSize());
        params.add(headlineQueryVo.getPageSize());
        return baseQuery(HeadlinePageVo.class, sql, params.toArray());
    }

    @Override
    public int findPageCount(HeadlineQueryVo headlineQueryVo) {
        List params = new ArrayList();
        String sql = """
                SELECT COUNT(1)
                FROM news_headline
                WHERE is_deleted = 0
                """;
        if (headlineQueryVo.getType() != 0) {
            sql = sql.concat(" AND type = ? ");
            params.add(headlineQueryVo.getType());
        }
        if (headlineQueryVo.getKeyWords() != null && !headlineQueryVo.getKeyWords().equals("")) {
            sql = sql.concat(" AND title LIKE ? ");
            params.add("%" + headlineQueryVo.getKeyWords() + "%");
        }
        return baseQueryObject(Long.class, sql, params.toArray()).intValue();
    }

    @Override
    public int incrPageViews(int hid) {
        String sql = """
                UPDATE news_headline
                SET page_views = page_views + 1
                WHERE hid = ?;
                """;
        return baseUpdate(sql, hid);
    }

    @Override
    public HeadlineDetailVo findHealineDetail(int hid) {
        String sql = """
                SELECT h.hid hid, h.title title, h.article article, h.type type, t.tname typeName, h.page_views pageViews, TIMESTAMPDIFF(HOUR, h.create_time, NOW())pastHours, h.publisher publisher, u.nick_name author
                FROM news_headline h
                LEFT JOIN news_type t
                ON h.type = t.tid
                LEFT JOIN news_user u
                ON h.publisher = u.uid
                WHERE h.hid = ?;
                """;
        List<HeadlineDetailVo> list = baseQuery(HeadlineDetailVo.class, sql, hid);
        return list != null && list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public int addNewsHeadline(NewsHeadline newsHeadline) {
        String sql = """
                INSERT INTO news_headline
                VALUES
                (DEFAULT, ?, ?, ?, ?, 0, NOW(), NOW(), 0)
                """;
        return baseUpdate(sql, newsHeadline.getTitle(), newsHeadline.getArticle(), newsHeadline.getType(), newsHeadline.getPublisher());
    }

    @Override
    public NewsHeadline findByHid(int hid) {
        String sql = """
                SELECT hid, title, article, type, publisher, page_views pageViews, create_time createTime, update_time updateTime, is_deleted isDeleted
                FROM news_headline
                WHERE hid = ?
                """;
        List<NewsHeadline> list = baseQuery(NewsHeadline.class, sql, hid);
        return list != null && list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public int update(NewsHeadline headline) {
        String sql = """
                UPDATE news_headline
                SET title = ?, article = ?, type = ?, update_time = NOW()
                WHERE hid = ?
                """;
        return baseUpdate(sql, headline.getTitle(), headline.getArticle(), headline.getType(), headline.getHid());
    }

    @Override
    public int removeByHid(int hid) {
        String sql = "UPDATE news_headline SET is_deleted = 1 WHERE hid = ?";
        return baseUpdate(sql, hid);
    }
}
