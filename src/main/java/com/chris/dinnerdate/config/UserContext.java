package com.chris.dinnerdate.config;

import com.chris.dinnerdate.model.User;

public class UserContext {

    private record UserInfo(Long id, String firstName, String email, Long currentLobbyId) {}

    private static final ThreadLocal<UserInfo> userThreadLocal = new ThreadLocal<>();

    public static void setContext(User user) {
        UserInfo userInfo = new UserInfo(
                user.getId(),
                user.getFirstName(),
                user.getEmail(),
                user.getLobbyId()
        );
        userThreadLocal.set(userInfo);
    }

    public static Long getId() {
        return userThreadLocal.get().id();
    }

    public static String getFirstName() {
        return userThreadLocal.get().firstName();
    }

    public static String getEmail() {
        return userThreadLocal.get().email();
    }

    public static Long getLobbyId() {
        return userThreadLocal.get().currentLobbyId();
    }

    public static void clear() {
        userThreadLocal.remove();
    }
}
