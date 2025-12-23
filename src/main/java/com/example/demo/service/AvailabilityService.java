package com.example.demo.service;

import com.example.demo.model.EmployeeAvailability;
import java.time.LocalDate;
import java.util.List;

public interface AvailabilityService {
    EmployeeAvailability createEmployeeAvailability(EmployeeAvailability availability);
    List<EmployeeAvailability> getByDate(LocalDate date);
    EmployeeAvailability update(Long id, EmployeeAvailability availability);
    void delete(Long id);
    List<EmployeeAvailability> getByEmployee(Long employeeId);
}