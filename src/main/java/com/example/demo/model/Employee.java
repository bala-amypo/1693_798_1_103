package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String role; 
    private Integer maxHoursPerWeek;
    
    // Add this field to fix the getSkills() error
    private String skills; 

    @PrePersist
    public void prePersist() {
        if (this.role == null || this.role.isEmpty()) {
            this.role = "STAFF";
        }
        if (this.maxHoursPerWeek != null && this.maxHoursPerWeek > 60) {
            throw new IllegalArgumentException("Max hours cannot exceed 60");
        }
    }
}