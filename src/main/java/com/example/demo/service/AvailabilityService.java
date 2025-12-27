package com.example.demo.service;

import com.example.demo.model.Availability;
import java.time.LocalDate;
import java.util.List;

public interface AvailabilityService {
    List<Availability> getAvailability(LocalDate date);
    List<Availability> getByEmployee(Long employeeId);
    Availability create(Availability availability);
    Availability update(Long id, Availability availability);
    void delete(Long id);
}