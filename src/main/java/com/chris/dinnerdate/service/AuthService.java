package com.chris.dinnerdate.service;

import com.chris.dinnerdate.config.TokenGenerator;
import com.chris.dinnerdate.config.UserContext;
import com.chris.dinnerdate.controller.AuthController.AuthResponse;
import com.chris.dinnerdate.controller.AuthController.LoginRequest;
import com.chris.dinnerdate.controller.AuthController.RegisterRequest;
import com.chris.dinnerdate.model.User;
import com.chris.dinnerdate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private static final int SESSION_ID_ENTROPY_IN_BYTES = 32;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenGenerator tokenGenerator;

    public AuthResponse registerUser(RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email address already in use");
        }

        User user = new User();
        user.setFirstName(registerRequest.firstName());
        user.setEmail(registerRequest.email());
        user.setPasswordHash(passwordEncoder.encode(registerRequest.password()));

        return createSessionAndSaveUser(user);
    }

    public AuthResponse login(LoginRequest loginRequest) {
        if (!userRepository.existsByEmail(loginRequest.email())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This email address has not been registered");
        }

        User user = userRepository.findByEmail(loginRequest.email());
        if (!passwordEncoder.matches(loginRequest.password(), user.getPasswordHash())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password is not valid");
        }

        return createSessionAndSaveUser(user);
    }

    private AuthResponse createSessionAndSaveUser(User user) {
        user.setSessionId(tokenGenerator.generate(SESSION_ID_ENTROPY_IN_BYTES));
        String sessionId = userRepository.save(user)
                .getSessionId();
        return new AuthResponse(sessionId);
    }

    public void logout() {
        userRepository.clearSession(UserContext.getId());
    }
}