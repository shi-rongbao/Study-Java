package com.shirongbao.headline.controller;

import com.shirongbao.headline.common.Result;
import com.shirongbao.headline.pojo.NewsHeadline;
import com.shirongbao.headline.service.NewsHeadlineService;
import com.shirongbao.headline.service.impl.NewsHeadlineServiceImpl;
import com.shirongbao.headline.util.JwtHelper;
import com.shirongbao.headline.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/headline/*")
public class NewsHeadlineController extends BaseController {
    private NewsHeadlineService headlineService = new NewsHeadlineServiceImpl();

    /**
     * 发布头条的接口实现
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void publish(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getHeader("token");
        Long userId = JwtHelper.getUserId(token);
        NewsHeadline newsHeadline = WebUtil.readJson(req, NewsHeadline.class);
        newsHeadline.setPublisher(userId.intValue());

        headlineService.addNewsHeadline(newsHeadline);
        WebUtil.writeJson(resp, Result.ok(null));
    }

    /**
     * 修改头条回显业务接口
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void findHeadlineByHid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int hid = Integer.parseInt(req.getParameter("hid"));
        NewsHeadline headline = headlineService.findByHid(hid);
        Map data = new HashMap();
        data.put("headline", headline);
        WebUtil.writeJson(resp, Result.ok(data));
    }

    /**
     * 更新头条的业务接口实现
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NewsHeadline headline = WebUtil.readJson(req, NewsHeadline.class);
        headlineService.update(headline);
        WebUtil.writeJson(resp, Result.ok(null));
    }

    /**
     * 删除头条的业务接口实现
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void removeByHid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int hid = Integer.parseInt(req.getParameter("hid"));
        headlineService.removeByHid(hid);
        WebUtil.writeJson(resp, Result.ok(null));
    }
}
