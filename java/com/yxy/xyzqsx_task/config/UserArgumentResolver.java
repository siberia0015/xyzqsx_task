package com.yxy.xyzqsx_task.config;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.yxy.xyzqsx_task.pojo.User;
import com.yxy.xyzqsx_task.service.IUserService;
import com.yxy.xyzqsx_task.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义用户参数
 */
@Component
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private IUserService userService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Class<?> parameterType = parameter.getParameterType();
        return parameterType == User.class;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

//        return UserContext.getUser();
        // 原方法
        HttpServletRequest nativeRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse nativeResponse = webRequest.getNativeResponse(HttpServletResponse.class);
        String userTicket = CookieUtil.getCookieValue(nativeRequest, "userTicket");
        if (StringUtils.isEmpty(userTicket)) {
            return null;
        }
        return userService.getUserByCookie(userTicket, nativeRequest, nativeResponse);
    }

}
