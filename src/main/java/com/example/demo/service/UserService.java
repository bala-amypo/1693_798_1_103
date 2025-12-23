package com.example.demo.service;

import com.example.demo.model.User;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email); // Add this
}