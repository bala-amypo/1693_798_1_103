package com.example.demo.service;

import com.example.demo.model.EmployeeAvailability;
import java.time.LocalDate;
import java.util.List;

public interface AvailabilityService {
    EmployeeAvailability create(EmployeeAvailability availability);
    List<EmployeeAvailability> getByDate(LocalDate date);
    EmployeeAvailability update(Long id, EmployeeAvailability details);
    void delete(Long id);
}