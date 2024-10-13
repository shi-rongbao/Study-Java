package com.shirongbao.schedule.controller;

import com.shirongbao.schedule.common.Result;
import com.shirongbao.schedule.common.ResultCodeEnum;
import com.shirongbao.schedule.pojo.SysUser;
import com.shirongbao.schedule.service.SysUserService;
import com.shirongbao.schedule.service.impl.SysUserServiceImpl;
import com.shirongbao.schedule.util.MD5Util;
import com.shirongbao.schedule.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/user/*")
public class SysUserController extends BaseController {
    SysUserService userService = new SysUserServiceImpl();

    /**
     * 接收用户注册请求的业务处理方法 (  业务接口, 与Java中的interface不同)
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 接收客户端传来的JSON串参数,并转换为SysUser对象并获取信息
        SysUser registUser = WebUtil.readJson(req, SysUser.class);
        int rows = userService.regist(registUser);
        Result<Object> result = Result.ok(null);
        if (rows < 1) {
            result = Result.build(null, ResultCodeEnum.USERNAME_USED);
        }
        WebUtil.writeJson(resp, result);
    }

    /**
     * 接收用户请求,完成登录的业务接口
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1 接收JSON串的登录用户数据,转换为sysUser对象
        SysUser sysUser = WebUtil.readJson(req, SysUser.class);
        Result result = null;
        // 2 调用服务层方法,根据用户名查询用户信息
        SysUser loginUser = userService.findByUsername(sysUser.getUsername());
        if (null == loginUser) {
            result = Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        } else if (!MD5Util.encrypt(sysUser.getUserPwd()).equals(loginUser.getUserPwd())) {
            result = Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
            // 3 判断密码是否匹配
        } else {
            Map data = new HashMap();
            loginUser.setUserPwd("");
            data.put("loginUser", loginUser);
            result = Result.ok(data);
        }
        WebUtil.writeJson(resp, result);
    }

    /**
     * 注册时,接收要注册的用户名,校验用户名是否被占用的业务接口
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void checkUsernameUsed(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 接收用户名
        String username = req.getParameter("username");
        // 调用服务层业务处理方法查询该用户是否有对应的用户
        SysUser sysUser = userService.findByUsername(username);
        // 如果有 相应已占用
        // 如果没有 响应 可用
        Result result = Result.ok(null);
        if (sysUser != null) {
            result = Result.build(null, ResultCodeEnum.USERNAME_USED);
        }
        WebUtil.writeJson(resp, result);
    }
}
