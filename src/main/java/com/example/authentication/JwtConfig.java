package com.example.authentication;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class JwtConfig {
    private String secretKey;
    private long sessionDuration;

    // Getter and setter for secretKey
    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    // Getter and setter for sessionDuration
    public long getSessionDuration() {
        return sessionDuration;
    }

    public void setSessionDuration(long sessionDuration) {
        this.sessionDuration = sessionDuration;
    }

    // Random key generator method
    public String generateRandomKey() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] keyBytes = new byte[32];
        secureRandom.nextBytes(keyBytes);
        return java.util.Base64.getEncoder().encodeToString(keyBytes);
    }
}
