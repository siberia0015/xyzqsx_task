package com.yxy.xyzqsx_task.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxy.xyzqsx_task.pojo.User;
import com.yxy.xyzqsx_task.exception.GlobalException;
import com.yxy.xyzqsx_task.mapper.UserMapper;
import com.yxy.xyzqsx_task.service.IUserService;
import com.yxy.xyzqsx_task.utils.CookieUtil;
import com.yxy.xyzqsx_task.utils.MD5Util;
import com.yxy.xyzqsx_task.utils.UUIDUtil;
import com.yxy.xyzqsx_task.utils.ValidatorUtil;
import com.yxy.xyzqsx_task.vo.LoginVo;
import com.yxy.xyzqsx_task.vo.RespBean;
import com.yxy.xyzqsx_task.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author yxy
 */
@Service
@Primary
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 登陆
     * @param loginVo
     * @param request
     * @param response
     * @return
     */
    @Override
    public RespBean doLongin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
//        参数校验
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
        }
        if (!ValidatorUtil.isMobile(mobile)) {
            return RespBean.error(RespBeanEnum.MOBILE_ERROR);
        }
        User user = userMapper.selectById(mobile);
        if (user == null) {
            throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
        }
//        System.out.println(MD5Util.formPassToDBPass(password, user.getSalt()));
        //判断密码是否正确
        if (!MD5Util.formPassToDBPass(password, user.getSalt()).equals(user.getPassword())) {
            throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
        }
        //生成Cookie
        String userTicket = UUIDUtil.uuid();
        //将用户信息存入redis
        redisTemplate.opsForValue().set("user:" + userTicket, user);
//      使用session
//        request.getSession().setAttribute(userTicket, user);
        CookieUtil.setCookie(request, response, "userTicket", userTicket);
        return RespBean.success(userTicket);

    }

    /**
     * 根据Cookie获取User
     * @param userTicket
     * @param request
     * @param response
     * @return
     */
    @Override
    public User getUserByCookie(String userTicket, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isEmpty(userTicket)) {
            return null;
        }
        User user = (User) redisTemplate.opsForValue().get("user:" + userTicket);
        if (user != null) {
            CookieUtil.setCookie(request, response, "userTicket", userTicket);
        }
        return user;
    }

    /**
     * 更新密码
     * @param userTicket
     * @param password
     * @param request
     * @param response
     * @return
     */
    @Override
    public RespBean updatePassword(String userTicket, String password, HttpServletRequest request, HttpServletResponse response) {
        User user = getUserByCookie(userTicket, request, response);
        if (user == null) {
            throw new GlobalException(RespBeanEnum.MOBILE_NOT_EXIST);
        }
        user.setPassword(MD5Util.inputPassToDBPass(password, user.getSalt()));
        int result = userMapper.updateById(user);
        if (1 == result) {
            //删除Redis
            redisTemplate.delete("user:" + userTicket);
            return RespBean.success();
        }
        return RespBean.error(RespBeanEnum.PASSWORD_UPDATE_FAIL);
    }


}
