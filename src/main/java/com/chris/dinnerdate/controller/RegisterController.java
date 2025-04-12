package com.chris.dinnerdate.controller;

import com.chris.dinnerdate.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegisterController {

    private final AuthService authService;

    public record RegisterRequest(String email, String password) {}

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest registerRequest) {
        authService.registerUser(registerRequest);
    }
}
