package com.example.demo.service;

import com.example.demo.model.EmployeeAvailability;
import java.util.List;

public interface AvailabilityService {
    List<EmployeeAvailability> getByEmployee(Long employeeId);
}