package com.example.demo.service.impl;

import com.example.demo.model.EmployeeAvailability;
import com.example.demo.repository.AvailabilityRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AvailabilityServiceImpl implements AvailabilityService {

    private AvailabilityRepository availabilityRepository;
    private EmployeeRepository employeeRepository;

    // MANDATORY: Test expects this constructor
    @Autowired
    public AvailabilityServiceImpl(AvailabilityRepository availabilityRepository, EmployeeRepository employeeRepository) {
        this.availabilityRepository = availabilityRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeAvailability> getByDate(LocalDate date) {
        // Test calls "getByDate"
        return availabilityRepository.findByAvailableDateAndAvailable(date, true);
    }

    @Override
    public EmployeeAvailability create(EmployeeAvailability availability) {
        // Test expects exception if exists
        if(availabilityRepository.findByEmployee_IdAndAvailableDate(
                availability.getEmployee().getId(), availability.getAvailableDate()).isPresent()) {
            throw new RuntimeException("Availability exists");
        }
        return availabilityRepository.save(availability);
    }

    @Override
    public EmployeeAvailability update(Long id, EmployeeAvailability details) {
        EmployeeAvailability existing = availabilityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
        // Update logic
        existing.setAvailable(details.getAvailable());
        return availabilityRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        EmployeeAvailability existing = availabilityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
        availabilityRepository.delete(existing);
    }
}