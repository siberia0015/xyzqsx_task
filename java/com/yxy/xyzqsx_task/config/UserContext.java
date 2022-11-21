package com.yxy.xyzqsx_task.config;

import com.yxy.xyzqsx_task.pojo.User;

public class UserContext {

    private static ThreadLocal<User> userThreadLocal = new ThreadLocal<>();

    public static void setUser(User tUser) {
        userThreadLocal.set(tUser);
    }

    public static User getUser() {
        return userThreadLocal.get();
    }
}
