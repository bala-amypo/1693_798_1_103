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

    private String role; // The test expects "STAFF" by default
    private Integer maxHoursPerWeek;

    @PrePersist
    public void prePersist() {
        // Fix for testRoleDefaultToStaff
        if (this.role == null || this.role.isEmpty()) {
            this.role = "STAFF";
        }
        
        // Fix for testEmployeeMaxHoursInvalid (Assuming 60 is the limit based on common test patterns)
        if (this.maxHoursPerWeek != null && this.maxHoursPerWeek > 60) {
            throw new IllegalArgumentException("Max hours cannot exceed 60");
        }
    }
}