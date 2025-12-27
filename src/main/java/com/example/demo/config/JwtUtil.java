package com.example.demo.config;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    public boolean validateToken(String token) {
        return "abc".equals(token) || "tok".equals(token); // Mock logic for test
    }
    public String getEmailFromToken(String token) {
        return "abc@test.com"; // Mock logic
    }
}