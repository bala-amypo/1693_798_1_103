package com.example.demo.service.impl;

import com.example.demo.model.EmployeeAvailability;
import com.example.demo.repository.AvailabilityRepository;
import com.example.demo.service.AvailabilityService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AvailabilityServiceImpl implements AvailabilityService {

    private final AvailabilityRepository availabilityRepository;

    public AvailabilityServiceImpl(AvailabilityRepository availabilityRepository) {
        this.availabilityRepository = availabilityRepository;
    }

    @Override
    public List<EmployeeAvailability> getByEmployee(Long employeeId) {
        return availabilityRepository.findByEmployeeId(employeeId);
    }
}