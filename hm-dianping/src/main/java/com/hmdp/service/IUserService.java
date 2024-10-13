package com.hmdp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hmdp.dto.LoginFormDTO;
import com.hmdp.dto.Result;
import com.hmdp.entity.User;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
public interface IUserService extends IService<User> {

    /**
     * 根据传入的手机号，还有session，创建验证码并发送，且需要存到session中
     * @param phone 手机号
     * @return 返回最终结果
     */
    Result sendCode(String phone);

    /**
     * 根据传入的登录信息，完成登录请求，同时将用户信息保存到session中
     * @param loginForm 用户登录信息
     * @return 返回结构
     */
    Result login(LoginFormDTO loginForm);
}
