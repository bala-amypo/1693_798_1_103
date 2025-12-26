package com.example.demo.controller;

import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.AvailabilityService;
import com.example.demo.model.EmployeeAvailability;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/availability")
public class AvailabilityController {
    private final AvailabilityService availabilityService;
    private final EmployeeRepository employeeRepository;

    // Required by Test line 531
    public AvailabilityController(AvailabilityService availabilityService, EmployeeRepository employeeRepository) {
        this.availabilityService = availabilityService;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/date")
    public List<EmployeeAvailability> byDate(@RequestParam String date) { // Required by Test line 533
        return availabilityService.getByDate(LocalDate.parse(date));
    }
}