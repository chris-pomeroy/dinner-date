package com.chris.dinnerdate.config;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;

@Component
public class TokenGenerator {

    private static final int ENTROPY_IN_BYTES = 32;

    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder().withoutPadding();

    public String generate() {
        byte[] randomBytes = new byte[ENTROPY_IN_BYTES];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }
}
