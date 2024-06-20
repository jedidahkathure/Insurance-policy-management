package com.example.usermanagementservice.Util;

public class JwtUtilBuilder {
    private String secret;

    public JwtUtilBuilder setSecret(String secret) {
        this.secret = secret;
        return this;
    }

    public JwtUtil createJwtUtil() {
        return new JwtUtil(secret);
    }
}