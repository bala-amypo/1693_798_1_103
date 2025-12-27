package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;
    private String role;
    private String skills;
    private int weeklyHours;

    // Constructor required by Test: testCreateEmployee
    public Employee(String fullName, String email, String role, String skills, int weeklyHours) {
        this.fullName = fullName;
        this.email = email;
        this.role = role;
        this.skills = skills;
        this.weeklyHours = weeklyHours;
    }
}