package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class EmployeeAvailability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private LocalDate availableDate;
    private Boolean available;

    // --- Add these Getters/Setters to fix the "cannot find symbol" errors ---
    public Long getId() { return id; }
    
    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }

    public LocalDate getAvailableDate() { return availableDate; }
    public void setAvailableDate(LocalDate availableDate) { this.availableDate = availableDate; }

    public Boolean getAvailable() { return available; }
    public void setAvailable(Boolean available) { this.available = available; }
}