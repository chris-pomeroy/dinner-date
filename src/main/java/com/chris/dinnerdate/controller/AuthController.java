package com.chris.dinnerdate.controller;

import com.chris.dinnerdate.service.AuthService;
import com.chris.dinnerdate.validation.Trimmed;
import com.chris.dinnerdate.validation.TrimmedAndLowerCase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    public record RegisterRequest(
            @Trimmed
            @NotBlank(message = "First name cannot be blank")
            String firstName,

            @TrimmedAndLowerCase
            @NotBlank(message = "Email cannot be blank")
            @Email(message = "Email is not valid")
            String email,

            @NotBlank(message = "Password cannot be blank")
            String password
    ) {}

    public record LoginRequest(
            @TrimmedAndLowerCase
            @NotBlank(message = "Email cannot be blank")
            @Email(message = "Email is not valid")
            String email,

            @NotBlank(message = "Password cannot be blank")
            String password
    ) {}

    public record AuthResponse(String sessionId) {}

    @PostMapping("/register")
    public AuthResponse register(@Valid @RequestBody RegisterRequest registerRequest) {
        return authService.registerUser(registerRequest);
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/logout")
    public void logout() {
        authService.logout();
    }
}
