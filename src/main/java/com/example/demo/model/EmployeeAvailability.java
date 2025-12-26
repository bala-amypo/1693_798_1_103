package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class EmployeeAvailability {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "employee_id")
    private Employee employee;

    private LocalDate availableDate;
    private Boolean available;

    // Constructor required by tests
    public EmployeeAvailability(Employee employee, LocalDate date, boolean available) {
        this.employee = employee;
        this.availableDate = date;
        this.available = available;
    }

    // Explicit setId for test compatibility
    public void setId(long id) {
        this.id = id;
    }
}