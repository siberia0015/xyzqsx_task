package com.yxy.xyzqsx_task.vo;

import com.yxy.xyzqsx_task.validator.IsMobile;

import javax.validation.constraints.NotNull;

/**
 * 登录传参
 *
 * @author: yxy
 * @ClassName: LoginVo
 */
public class LoginVo {

    @NotNull
    @IsMobile
    private String mobile;

    @NotNull
//    @Length(min = 32)
    private String password;

    @Override
    public String toString() {
        return "LoginVo{" +
                "mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
