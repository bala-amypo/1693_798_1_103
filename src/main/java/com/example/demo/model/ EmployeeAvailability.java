package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "employee_availabilities")
@Data // This generates getEmployee(), getAvailableDate(), and getAvailable()
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Employee is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @NotNull(message = "Date is required")
    private LocalDate availableDate; // Matches the Service error: getAvailableDate()

    @NotNull(message = "Availability status is required")
    private Boolean available; // Matches the Service error: getAvailable()
}