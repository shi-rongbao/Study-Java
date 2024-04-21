package com.shirongbao.schedule.controller;

import com.shirongbao.schedule.common.Result;
import com.shirongbao.schedule.pojo.SysSchedule;
import com.shirongbao.schedule.service.SysScheduleService;
import com.shirongbao.schedule.service.impl.SysScheduleServiceImpl;
import com.shirongbao.schedule.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 由于这个servlet后续可能做增删改查任意一种操作,因此在uri后面再增加 标志代表这次请求什么操作
 * /schedule/add        增加
 * /schedule/find       查询
 * /schedule/update     修改
 * /schedule/remove     删除
 */
@WebServlet("/schedule/*")
public class SysScheduleController extends BaseController {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    private SysScheduleService scheduleService = new SysScheduleServiceImpl();

    protected void removeSchedule(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 接收请求中的sid参数
        int sid = Integer.parseInt(req.getParameter("sid"));
        // 调用服务层的方法,删除sid对应的数据
        scheduleService.removeSchedule(sid);
        // 响应删除成功信息
        WebUtil.writeJson(resp, Result.ok(null));
    }

    protected void updateSchedule(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 接收请求体中的JSON串,并转换成一个sysUser对象
        SysSchedule schedule = WebUtil.readJson(req, SysSchedule.class);
        // 调用服务层方法,将数据更新进入数据库
        scheduleService.updateSchedule(schedule);
        // 响应给客户端
        WebUtil.writeJson(resp, Result.ok(null));

    }

    protected void addDefaultSchedule(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 接收请求的uid参数
        int uid = Integer.parseInt(req.getParameter("uid"));
        // 调用服务层的方法,向数据库中增加一条空记录
        scheduleService.addDefault(uid);
        WebUtil.writeJson(resp,Result.ok(null));
    }

    protected void findAllSchedule(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 接收请求uid参数
        int uid = Integer.parseInt(req.getParameter("uid"));
        //查询用户所有日程
        List<SysSchedule> itemList = scheduleService.findItemListByUid(uid);
        // 将日程信息放入Result
        Map data = new HashMap();
        data.put("itemList", itemList);
        Result result = Result.ok(data);
        // 将Result对象转换为JSON字符串响应给客户端
        WebUtil.writeJson(resp, result);
    }

}
