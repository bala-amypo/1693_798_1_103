package com.example.demo.service.impl;

import com.example.demo.model.EmployeeAvailability;
import com.example.demo.repository.AvailabilityRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.AvailabilityService;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class AvailabilityServiceImpl implements AvailabilityService {
    private final AvailabilityRepository availabilityRepository;
    private final EmployeeRepository employeeRepository;

    // Required by Test line 44
    public AvailabilityServiceImpl(AvailabilityRepository availabilityRepository, EmployeeRepository employeeRepository) {
        this.availabilityRepository = availabilityRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeAvailability create(EmployeeAvailability availability) { // Required by Test line 247
        return availabilityRepository.save(availability);
    }

    @Override
    public List<EmployeeAvailability> getByDate(LocalDate date) {
        return availabilityRepository.findByAvailableDate(date);
    }

    @Override
    public List<EmployeeAvailability> getByEmployee(Long employeeId) {
        return availabilityRepository.findByEmployeeId(employeeId);
    }
}