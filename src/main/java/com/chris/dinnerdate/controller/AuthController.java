package com.chris.dinnerdate.controller;

import com.chris.dinnerdate.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    public record RegisterRequest(String firstName, String email, String password) {}
    public record LoginRequest(String firstName, String email, String password) {}
    public record AuthResponse(String sessionId) {}

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest registerRequest) {
        return authService.registerUser(registerRequest);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/logout")
    public void logout() {
        authService.logout();
    }
}
