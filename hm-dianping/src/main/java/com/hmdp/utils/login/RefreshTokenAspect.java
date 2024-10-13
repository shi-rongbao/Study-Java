package com.hmdp.utils.login;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.hmdp.dto.UserDTO;
import com.hmdp.utils.UserHolder;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.hmdp.utils.RedisConstants.LOGIN_USER_KEY;
import static com.hmdp.utils.RedisConstants.LOGIN_USER_TTL;

/**
 * @author ShiRongbao
 * @create 2024/8/4 23:02
 * @description:
 */
/*@Aspect
@Component*/
public class RefreshTokenAspect {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Before("execution(public * com.hmdp.controller..*(..))")
    public void process() {
        // 获取当前请求属性
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            // 获取请求头中的token
            String token = request.getHeader("authorization");
            if (StrUtil.isBlank(token)) {
                return;
            }
            // 基于token获取redis中的用户
            Map<Object, Object> userMap = stringRedisTemplate.opsForHash().entries(LOGIN_USER_KEY + token);
            // 判断用户是否存在
            if (userMap.isEmpty()) {
                return;
            }
            // 将查询到的Hash数据转为UserDTO对象
            UserDTO userDTO = BeanUtil.fillBeanWithMap(userMap, new UserDTO(), false);
            // 存在，保存用户信息到ThreadLocal
            UserHolder.saveUser(userDTO);
            // 刷新token有效期
            stringRedisTemplate.expire(LOGIN_USER_KEY + token, LOGIN_USER_TTL, TimeUnit.SECONDS);
        }
    }

    /*@After("execution(public * com.hmdp.controller..*(..))")
    public void clearUser() {
        UserHolder.removeUser();
    }*/

}
