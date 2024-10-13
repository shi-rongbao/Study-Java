package com.hmdp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.dto.LoginFormDTO;
import com.hmdp.dto.Result;
import com.hmdp.dto.UserDTO;
import com.hmdp.entity.User;
import com.hmdp.mapper.UserMapper;
import com.hmdp.service.IUserService;
import com.hmdp.utils.PasswordEncoder;
import com.hmdp.utils.RegexUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.sql.Time;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.hmdp.utils.RedisConstants.*;
import static com.hmdp.utils.SystemConstants.USER_NICK_NAME_PREFIX;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private UserMapper userMapper;

    @Override
    public Result sendCode(String phone) {
        // 1. 校验手机号
        if (RegexUtils.isPhoneInvalid(phone)) {
            // 2. 如果不符合，返回错误信息
            return Result.fail("手机号格式有误！");
        }
        // 3. 符合，生成验证码
        String code = RandomUtil.randomNumbers(6);
        // 4. 保存验证码到redis
        // stringRedisTemplate.opsForValue().set(LOGIN_CODE_KEY + phone, code, LOGIN_CODE_TTL, TimeUnit.MINUTES);
        // 保存手机号与验证码
        stringRedisTemplate.opsForHash().put(LOGIN_CODE_KEY + phone, phone, code);
        stringRedisTemplate.expire(LOGIN_CODE_KEY + phone, LOGIN_CODE_TTL, TimeUnit.MINUTES);
        // 5. 发送验证码  模拟发送验证码
        log.debug("验证码发送成功，验证码{}", code);
        // 返回ok
        return Result.ok();
    }

    @Override
    public Result login(LoginFormDTO loginForm) {
        String phone = loginForm.getPhone();
        User user = null;
        // 1. 校验手机号
        if (RegexUtils.isPhoneInvalid(phone)) {
            // 2. 如果不符合，返回错误信息
            return Result.fail("手机号格式有误！");
        }
        String password = loginForm.getPassword();
        if (StrUtil.isNotBlank(password)) {
            // 这里说明用密码登录
            user = query().eq("phone", phone).one();
            if (user == null) {
                // 这里说明用户不存在，创建新的用户
                user = createUserWithPhone(phone);
            }
            String encodedPassword = user.getPassword();
            if (!PasswordEncoder.matches(encodedPassword, password)) {
                log.debug("密码输入错误！");
                return Result.fail("密码输入错误！");
            }
        } else {
            // 这里说明使用验证码登录
            // 2. 从redis获取验证码并校验
            Object cacheCode = stringRedisTemplate.opsForHash().get(LOGIN_CODE_KEY + phone, phone);
            if (cacheCode == null) {
                return Result.fail("验证码已过期！");
            }
            String code = loginForm.getCode();
            if (!cacheCode.equals(code)) {
                // 3. 不一致，报错
                return Result.fail("验证码有误！");
            }
            stringRedisTemplate.delete(LOGIN_CODE_KEY + phone);
            // 4. 一致，根据手机号查询用户
            user = query().eq("phone", phone).one();
            // 5. 判断用户是否存在
            if (user == null) {
                // 6. 不存在，创建新用户并保存
                user = createUserWithPhone(phone);
            }
        }
        // 7. 保存用户信息到redis中
        // 7.1 生成token，作为登录令牌
        String token = UUID.randomUUID().toString(true);
        // 7.2 将UserDTO对象转为HashMap存储
        UserDTO userDTO = BeanUtil.copyProperties(user, UserDTO.class);
        // 使用StringRedisTemplate，因此保证所有值必须是String类型
        Map<String, Object> uerMap = BeanUtil.beanToMap(userDTO, new HashMap<>(),
                CopyOptions.create()
                        .setIgnoreNullValue(true)
                        .setFieldValueEditor((fieldName, fieldValue) -> fieldValue.toString()));
        // 7.3 存储
        String tokenKey = LOGIN_USER_KEY + token;
        stringRedisTemplate.opsForHash().putAll(tokenKey, uerMap);
        // 7.4 设置token有效期 30分钟
        stringRedisTemplate.expire(tokenKey, LOGIN_USER_TTL, TimeUnit.SECONDS);
        // 8. 将token返回给前端
        return Result.ok(token);
    }

    private User createUserWithPhone(String phone) {
        // 创建用户信息
        User user = new User();
        user.setPhone(phone);
        user.setNickName(USER_NICK_NAME_PREFIX + RandomUtil.randomString(10));
        // 保存用户
        save(user);
        return user;
    }

}
