package com.chris.dinnerdate.config;

import com.chris.dinnerdate.model.User;

public class UserContext {

    private record UserInfo(Long id, String email) {}

    private static final ThreadLocal<UserInfo> userThreadLocal = new ThreadLocal<>();

    public static void setContext(User user) {
        userThreadLocal.set(new UserInfo(user.getId(), user.getEmail()));
    }

    public static Long getId() {
        return userThreadLocal.get().id();
    }

    public static String getEmail() {
        return userThreadLocal.get().email();
    }

    public static void clear() {
        userThreadLocal.remove();
    }
}
