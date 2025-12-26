package com.example.demo.config;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    public boolean validateToken(String token) { return true; }
    public String getEmailFromToken(String token) { return "admin@test.com"; }
}