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

    public AvailabilityServiceImpl(AvailabilityRepository ar, com.example.demo.repository.EmployeeRepository er) {
        this.availabilityRepository = ar;
    }

    @Override
    public EmployeeAvailability create(EmployeeAvailability availability) {
        if (availability.getEmployee() != null && availability.getAvailableDate() != null) {
            availabilityRepository.findByEmployee_IdAndAvailableDate(
                availability.getEmployee().getId(), availability.getAvailableDate()
            ).ifPresent(s -> { throw new RuntimeException("exists"); });
        }
        return availabilityRepository.save(availability);
    }

    @Override
    public EmployeeAvailability update(Long id, EmployeeAvailability availability) {
        return availabilityRepository.findById(id).map(existing -> {
            existing.setAvailableDate(availability.getAvailableDate());
            existing.setAvailable(availability.getAvailable());
            return availabilityRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("not found"));
    }

    @Override
    public void delete(Long id) {
        EmployeeAvailability av = availabilityRepository.findById(id).orElse(null);
        if (av != null) availabilityRepository.delete(av);
    }

    @Override
    public List<EmployeeAvailability> getByDate(LocalDate date) {
        return availabilityRepository.findByAvailableDate(date);
    }

    @Override
    public List<EmployeeAvailability> getByEmployee(Long employeeId) {
        // Use underscore to match findByEmployee_Id in repository
        return availabilityRepository.findByEmployee_Id(employeeId);
    }
}