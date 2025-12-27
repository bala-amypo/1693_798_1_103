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

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> skills = new ArrayList<>();

    // Compatibility constructor
    public Employee(String fullName, String email, String role, String ignoredDepartment, int maxHours) {
        this.fullName = fullName;
        this.email = email;
        this.role = role;
        setMaxHoursPerWeek((double) maxHours); // Use setter for validation
        this.skills = new ArrayList<>();
    }

    // --- FIX: Validation Logic for Max Hours ---
    public void setMaxHoursPerWeek(Double maxHoursPerWeek) {
        if (maxHoursPerWeek < 0 || maxHoursPerWeek > 168) {
            throw new IllegalArgumentException("Invalid max hours per week");
        }
        this.maxHoursPerWeek = maxHoursPerWeek;
    }

    // --- FIX: Skill Matching Helper ---
    public boolean hasSkill(String requiredSkill) {
        if (requiredSkill == null || skills == null) return false;
        return skills.stream().anyMatch(s -> s.equalsIgnoreCase(requiredSkill));
    }
}