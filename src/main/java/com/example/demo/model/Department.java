package com.example.demo.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Department {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String location; 
    private LocalDateTime createdAt;

    public Department(String name, String description, String location) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.createdAt = LocalDateTime.now();
    }
    // Required for the scheduling logic
    public String getRequiredSkills() { return location; }
}