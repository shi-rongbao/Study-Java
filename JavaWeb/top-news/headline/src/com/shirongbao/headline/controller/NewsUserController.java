package com.shirongbao.headline.controller;

import com.shirongbao.headline.common.Result;
import com.shirongbao.headline.common.ResultCodeEnum;
import com.shirongbao.headline.pojo.NewsUser;
import com.shirongbao.headline.service.NewsUserService;
import com.shirongbao.headline.service.impl.NewsUserServiceImpl;
import com.shirongbao.headline.util.JwtHelper;
import com.shirongbao.headline.util.MD5Util;
import com.shirongbao.headline.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/user/*")
public class NewsUserController extends BaseController {
    private NewsUserService userService = new NewsUserServiceImpl();

    /**
     * 处理登录表单提交的业务接口的实现
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 接收用户名和密码
        NewsUser paramUser = WebUtil.readJson(req, NewsUser.class);
        // 调用服务层方法,实现登录
        NewsUser loginUser = userService.findByUsername(paramUser.getUsername());
        Result result = null;
        if (null != loginUser) {
            if (MD5Util.encrypt(paramUser.getUserPwd()).equalsIgnoreCase(loginUser.getUserPwd())) {
                Map data = new HashMap();
                data.put("token", JwtHelper.createToken(loginUser.getUid().longValue()));
                result = Result.ok(data);
            } else {
                result = Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
            }
        } else {
            result = Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }
        // 向客户端响应登录验证信息
        WebUtil.writeJson(resp, result);
    }

    /**
     * 根据token口令获得用户信息的接口实现
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void getUserInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getHeader("token");
        Result result = Result.build(null, ResultCodeEnum.NOTLOGIN);
        if (null != token && (!"".equals(token))) {
            if (!JwtHelper.isExpiration(token)) {
                Integer userId = JwtHelper.getUserId(token).intValue();
                NewsUser user = userService.findByUid(userId);
                if (null != user) {
                    Map data = new HashMap();
                    user.setUserPwd("");
                    data.put("loginUser", user);
                    result = Result.ok(data);
                }
            }
        }
        WebUtil.writeJson(resp, result);
    }

    // 校验用户名是否被占用的业务接口实现
    protected void checkUserName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取用户账号
        String username = req.getParameter("username");
        // 根据用户名查询用户信息  找到了返回505,找不到返回200
        NewsUser user = userService.findByUsername(username);
        Result result = Result.ok(null);
        if (null != user) {
            result = Result.build(null, ResultCodeEnum.USERNAME_USED);
        }
        WebUtil.writeJson(resp, result);
    }

    // 完成注册的业务接口
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 接收JSON信息
        NewsUser registUser = WebUtil.readJson(req, NewsUser.class);
        // 调用服务层将用户信息存入数据库
        Integer rows = userService.registUser(registUser);
        // 根据存入是否成功处理响应值
        Result result = Result.ok(null);
        if (rows == 0) {
            result = Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }
        WebUtil.writeJson(resp, result);
    }

    /**
     * 前端自己校验是否市区登录状态的接口
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void checkLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getHeader("token");
        Result result = Result.build(null, ResultCodeEnum.NOTLOGIN);
        if (null != token) {
            if (!JwtHelper.isExpiration(token)) {
                result = Result.ok(null);
            }
        }
        WebUtil.writeJson(resp, result);
    }
}
