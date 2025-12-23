package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDateTime createdAt;

    public String getName() { return name; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}