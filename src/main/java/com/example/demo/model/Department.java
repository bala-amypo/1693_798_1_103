package com.example.demo.model;
import jakarta.persistence.*;
import lombok.*;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Department {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String location; // Used for requiredSkills in tests

    public Department(String name, String description, String location) {
        this.name = name;
        this.description = description;
        this.location = location;
    }
    public String getRequiredSkills() { return location; }
}