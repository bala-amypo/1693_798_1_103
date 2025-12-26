package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data; // If using Lombok

@Entity
@Data // This generates getters/setters automatically
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String fullName;
    private String email;
    private String role;
    private String skills;
    private int experience;
    
    // ADD THIS FIELD
    private Integer maxHoursPerWeek; 

    // If NOT using Lombok, add these manually:
    public Integer getMaxHoursPerWeek() { return maxHoursPerWeek; }
    public void setMaxHoursPerWeek(Integer maxHoursPerWeek) { this.maxHoursPerWeek = maxHoursPerWeek; }
}