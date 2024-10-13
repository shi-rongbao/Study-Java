package com.shirongbao.headline.controller;

import com.shirongbao.headline.common.Result;
import com.shirongbao.headline.pojo.NewsType;
import com.shirongbao.headline.pojo.vo.HeadlineDetailVo;
import com.shirongbao.headline.pojo.vo.HeadlineQueryVo;
import com.shirongbao.headline.service.NewsHeadlineService;
import com.shirongbao.headline.service.NewsTypeService;
import com.shirongbao.headline.service.impl.NewsHeadlineServiceImpl;
import com.shirongbao.headline.service.impl.NewsTypeServiceImpl;
import com.shirongbao.headline.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 门户控制器
// 那些不需要登录,不需要做增删改的门户页的请求都放在这里
@WebServlet("/portal/*")
public class PortalController extends BaseController {
    private NewsTypeService typeService = new NewsTypeServiceImpl();
    private NewsHeadlineService headlineService = new NewsHeadlineServiceImpl();


    /**
     * 查询所有新闻类型
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void findAllTypes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<NewsType> newsTypeList = typeService.findAll();
        WebUtil.writeJson(resp, Result.ok(newsTypeList));
    }

    // 分页查询头条信息的接口实现
    protected void findNewsPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 接收请求中的参数
        HeadlineQueryVo headlineQueryVo = WebUtil.readJson(req, HeadlineQueryVo.class);
        // 将参数传递给服务层    进行分页查询
        Map pageInfo = headlineService.findPage(headlineQueryVo);
        Map data = new HashMap();
        data.put("pageInfo", pageInfo);
        // 将分页查询的结果转成JSON串  响应给客户端
        WebUtil.writeJson(resp, Result.ok(data));
    }

    /**
     * 查询头条详情的业务接口实现
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showHeadlineDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 接收要查询头条的hid
        int hid = Integer.parseInt(req.getParameter("hid"));
        // 调用服务层完成查询处理
        HeadlineDetailVo headlineDetailVo = headlineService.findHeadLineDetail(hid);
        Map data = new HashMap();
        data.put("headline", headlineDetailVo);
        // 将查到的信息响应给客户端
        WebUtil.writeJson(resp, Result.ok(data));
    }
}
