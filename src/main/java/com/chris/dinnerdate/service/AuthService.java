package com.chris.dinnerdate.service;

import com.chris.dinnerdate.config.UserContext;
import com.chris.dinnerdate.model.User;
import com.chris.dinnerdate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.SecureRandom;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder().withoutPadding();

    public void registerUser(String email, String password) {
        if (userRepository.existsByEmail(email)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email address already in use");
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        userRepository.save(user);
    }

    public String login(String email, String password) {
        if (!userRepository.existsByEmail(email)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This email address has not been registered");
        }

        User user = userRepository.findByEmail(email);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password is not valid");
        }

        byte[] randomBytes = new byte[32];
        secureRandom.nextBytes(randomBytes);
        String sessionID = base64Encoder.encodeToString(randomBytes);

        user.setSessionId(sessionID);
        userRepository.save(user);
        return sessionID;
    }

    public void logout() {
        userRepository.clearSession(UserContext.getId());
    }
}