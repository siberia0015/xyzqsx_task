package com.yxy.xyzqsx_task.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxy.xyzqsx_task.pojo.User;
import com.yxy.xyzqsx_task.vo.LoginVo;
import com.yxy.xyzqsx_task.vo.RespBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author yxy
 */

public interface IUserService extends IService<User> {

    /**
     * 登录方法
     *
     * @param loginVo
     * @param request
     * @param response
     * @return com.yxy.xyzqsx_task.vo.RespBean
     * @author yxy
     * @operation add
     **/
    RespBean doLongin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response);

    /**
     * 根据cookie获取用户
     *
     * @param userTicket
     * @return com.yxy.xyzqsx_task.pojo.TUser
     * @author yxy
     * @operation add
     **/
    User getUserByCookie(String userTicket, HttpServletRequest request, HttpServletResponse response);


    /**
     * 更新密码
     *
     * @param userTicket
     * @param password
     * @return com.yxy.xyzqsx_task.vo.RespBean
     * @operation add
     **/
    RespBean updatePassword(String userTicket, String password, HttpServletRequest request, HttpServletResponse response);
}
