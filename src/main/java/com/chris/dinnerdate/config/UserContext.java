package com.chris.dinnerdate.config;

import com.chris.dinnerdate.model.User;

public class UserContext {

    private record UserInfo(Long id, String email, Long currentLobbyId) {}

    private static final ThreadLocal<UserInfo> userThreadLocal = new ThreadLocal<>();

    public static void setContext(User user) {
        userThreadLocal.set(new UserInfo(user.getId(), user.getEmail(), user.getCurrentLobbyId()));
    }

    public static Long getId() {
        return userThreadLocal.get().id();
    }

    public static String getEmail() {
        return userThreadLocal.get().email();
    }

    public static Long getCurrentLobbyId() {
        return userThreadLocal.get().currentLobbyId();
    }

    public static void clear() {
        userThreadLocal.remove();
    }
}
