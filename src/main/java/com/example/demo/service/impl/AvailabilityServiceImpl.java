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

    public AvailabilityServiceImpl(AvailabilityRepository availabilityRepository, EmployeeRepository employeeRepository) {
        this.availabilityRepository = availabilityRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeAvailability create(EmployeeAvailability availability) {
        return availabilityRepository.save(availability);
    }

    @Override
    public EmployeeAvailability update(Long id, EmployeeAvailability availability) {
        return availabilityRepository.findById(id)
            .map(existing -> {
                existing.setAvailableDate(availability.getAvailableDate());
                existing.setAvailable(availability.getAvailable());
                existing.setEmployee(availability.getEmployee());
                return availabilityRepository.save(existing);
            })
            .orElseThrow(() -> new RuntimeException("Availability record not found"));
    }

    @Override
    public void delete(Long id) {
        availabilityRepository.deleteById(id);
    }

    @Override
    public List<EmployeeAvailability> getByDate(LocalDate date) {
        return availabilityRepository.findByAvailableDate(date);
    }

    @Override
    public List<EmployeeAvailability> getByEmployee(Long employeeId) {
        // This call will now work because findByEmployeeId is defined in the repo
        return availabilityRepository.findByEmployeeId(employeeId);
    }
}