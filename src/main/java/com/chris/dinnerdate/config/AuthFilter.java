package com.chris.dinnerdate.config;

import com.chris.dinnerdate.model.User;
import com.chris.dinnerdate.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {

    private static final Map<String, HttpMethod> WHITELIST = Map.of(
            "/login", HttpMethod.POST,
            "/register", HttpMethod.POST,
            "/health", HttpMethod.GET,
            "/recipe-images/", HttpMethod.GET,
            "/join", HttpMethod.GET
    );

    private static final String BEARER_PREFIX = "Bearer ";

    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        if (isWhitelisted(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        String sessionId = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (sessionId == null || !sessionId.startsWith(BEARER_PREFIX)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }

        sessionId = sessionId.substring(BEARER_PREFIX.length());
        User user = userRepository.findBySessionId(sessionId);
        if (user == null) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }

        try {
            UserContext.setContext(user);
            filterChain.doFilter(request, response);
        } finally {
            UserContext.clear();
        }
    }

    private boolean isWhitelisted(HttpServletRequest request) {
        String path = request.getRequestURI();
        String method = request.getMethod();
        return WHITELIST.entrySet()
                .stream()
                .anyMatch(entry -> path.startsWith(entry.getKey()) && method.equals(entry.getValue().toString()));
    }
}
