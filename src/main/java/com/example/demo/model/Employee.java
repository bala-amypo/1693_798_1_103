package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String role;
    private String skills;
    private Integer maxHoursPerWeek;

    // Custom constructor required by the Test Suite
    public Employee(String name, String email, String role, String skills, int maxHoursPerWeek) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.skills = skills;
        this.maxHoursPerWeek = maxHoursPerWeek;
    }

    // Alias methods to satisfy the Test Suite calling .getFullName()
    public String getFullName() { return this.name; }
    public void setFullName(String name) { this.name = name; }

    @PrePersist
    public void prePersist() {
        if (this.role == null || this.role.isEmpty()) {
            this.role = "STAFF";
        }
    }
}