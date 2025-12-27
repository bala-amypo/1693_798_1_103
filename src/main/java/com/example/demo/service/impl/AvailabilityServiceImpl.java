package com.example.demo.service.impl;

import com.example.demo.model.Availability;
import com.example.demo.model.Employee;
import com.example.demo.repository.AvailabilityRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AvailabilityServiceImpl implements AvailabilityService {

    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Availability> getAvailability(LocalDate date) {
        if (date == null) {
            return availabilityRepository.findAll();
        }
        return availabilityRepository.findByDate(date);
    }

    // Renamed to match Interface
    @Override
    public List<Availability> getByEmployee(Long employeeId) {
        return availabilityRepository.findByEmployee_Id(employeeId);
    }

    // Renamed to match Interface
    @Override
    public Availability create(Availability availability) {
        if (availability.getEmployee() != null && availability.getEmployee().getId() != null) {
             Optional<Employee> emp = employeeRepository.findById(availability.getEmployee().getId());
             emp.ifPresent(availability::setEmployee);
        }

        Availability existing = availabilityRepository.findByEmployee_IdAndDate(
                availability.getEmployee().getId(), 
                availability.getDate()
        );

        if (existing != null) {
            existing.setDate(availability.getDate());
            return availabilityRepository.save(existing);
        }

        return availabilityRepository.save(availability);
    }

    // Renamed to match Interface
    @Override
    public void delete(Long id) {
        availabilityRepository.deleteById(id);
    }

    // Renamed to match Interface
    @Override
    public Availability update(Long id, Availability availability) {
        return availabilityRepository.findById(id).map(existing -> {
            existing.setDate(availability.getDate());
            return availabilityRepository.save(existing);
        }).orElse(null);
    }
}