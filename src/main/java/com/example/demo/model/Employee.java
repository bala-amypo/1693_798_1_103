package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String fullName;
    private String email;
    private String role;
    private Double maxHoursPerWeek;

    @ElementCollection
    private List<String> skills = new ArrayList<>();

    // --- COMPATIBILITY CONSTRUCTOR FOR TESTS ---
    // The test calls: new Employee(String, String, String, String, int)
    // We map this to the current fields.
    public Employee(String fullName, String email, String role, String ignoredDepartment, int maxHours) {
        this.fullName = fullName;
        this.email = email;
        this.role = role;
        // We ignore the 4th argument (department) as it was removed from the model
        this.maxHoursPerWeek = (double) maxHours;
        this.skills = new ArrayList<>();
    }
}