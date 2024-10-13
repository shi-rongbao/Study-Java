package com.atguigu.login;

import com.atguigu.constant.RedisConstant;
import com.atguigu.entity.UserInfo;
import com.atguigu.execption.GuiguException;
import com.atguigu.result.ResultCodeEnum;
import com.atguigu.util.AuthContextHolder;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;

/**
 * @author ShiRongbao
 * @create 2024/5/13 14:44
 * @description:
 */
@Aspect
@Component
public class LoginAspect {

    @Resource
    private RedisTemplate redisTemplate;

    @Around("@annotation(com.atguigu.login.TingShuLogin)")
    public Object process(ProceedingJoinPoint joinPoint) throws Throwable {
        // 拿到请求里面的token
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String token = request.getHeader("token");
        // 拿到目标方法上的注解
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 先获取到目标方法
        Method targetMethod = signature.getMethod();
        // 拿到目标方法上的注解TingShu
        TingShuLogin tingShuLogin = targetMethod.getAnnotation(TingShuLogin.class);
        // 当需要登录
        if (tingShuLogin.required()) {
            // 先判断token是否为空，为空说明需要提示登录
            if (StringUtils.isEmpty(token)) {
                // 需要登录，返回响应码208，代表未登录，小程序端处理，提示登录
                // TODO
                throw new GuiguException(ResultCodeEnum.UN_LOGIN);
            }
            // 从redis中通过key+token拿到用户数据
            UserInfo userInfo = (UserInfo) redisTemplate.opsForValue().get(RedisConstant.USER_LOGIN_KEY_PREFIX + token);
            // 如果用户数据为null，说明key过期了，需要重新登陆
            if (userInfo == null) {
                throw new GuiguException(ResultCodeEnum.UN_LOGIN);
            }
        }
        // 这里是不需要登录，或者是需要登录，且用户已经登录后，执行的操作
        // 看redis中有没有登录信息
        if (!StringUtils.isEmpty(token)) {
            // 拿到token，则从redis中查询用户信息
            UserInfo userInfo = (UserInfo) redisTemplate.opsForValue().get(RedisConstant.USER_LOGIN_KEY_PREFIX + token);
            // 如果redis中有数据，把用户部分数据放到共享区域
            if (userInfo != null) {
                // AuthContextHolder封装了ThreadLocal
                AuthContextHolder.setUserId(userInfo.getId());
                AuthContextHolder.setUsername(userInfo.getNickname());
            }
        }
        // 继续执行方法
        return joinPoint.proceed();
    }
}
