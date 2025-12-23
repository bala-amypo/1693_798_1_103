package com.example.demo.service.impl;

import com.example.demo.model.EmployeeAvailability;
import com.example.demo.repository.AvailabilityRepository;
import com.example.demo.service.AvailabilityService;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class AvailabilityServiceImpl implements AvailabilityService {

    private final AvailabilityRepository availabilityRepository;

    public AvailabilityServiceImpl(AvailabilityRepository availabilityRepository) {
        this.availabilityRepository = availabilityRepository;
    }

    @Override
    public EmployeeAvailability createEmployeeAvailability(EmployeeAvailability availability) {
        return availabilityRepository.save(availability);
    }

    @Override
    public List<EmployeeAvailability> getByDate(LocalDate date) {
        // Ensure this method exists in your AvailabilityRepository
        return availabilityRepository.findByAvailableDate(date);
    }

    @Override
    public EmployeeAvailability update(Long id, EmployeeAvailability availabilityDetails) {
        EmployeeAvailability availability = availabilityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Availability not found for id: " + id));
        
        availability.setAvailableDate(availabilityDetails.getAvailableDate());
        availability.setAvailable(availabilityDetails.getAvailable());
        availability.setEmployee(availabilityDetails.getEmployee());
        
        return availabilityRepository.save(availability);
    }

    @Override
    public void delete(Long id) {
        availabilityRepository.deleteById(id);
    }

    @Override
    public List<EmployeeAvailability> getByEmployee(Long employeeId) {
        return availabilityRepository.findByEmployeeId(employeeId);
    }
}