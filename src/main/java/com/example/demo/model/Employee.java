package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor // Required for JPA
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String fullName;
    private String email;
    private String role;
    private String skills;
    private int experience;
    private Integer maxHoursPerWeek;

    // ADD THIS CONSTRUCTOR for the test suite
    public Employee(String fullName, String email, String role, String skills, int experience) {
        this.fullName = fullName;
        this.email = email;
        this.role = role;
        this.skills = skills;
        this.experience = experience;
    }
}