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

    public record AuthRequest(String firstName, String email, String password) {}

    @PostMapping("/register")
    public void register(@RequestBody AuthRequest authRequest) {
        authService.registerUser(authRequest);
    }

    public record LoginResponse(String sessionId) {}

    @PostMapping("/login")
    public LoginResponse login(@RequestBody AuthRequest authRequest) {
        String sessionID = authService.login(authRequest.email(), authRequest.password());
        return new LoginResponse(sessionID);
    }

    @PostMapping("/logout")
    public void logout() {
        authService.logout();
    }
}
