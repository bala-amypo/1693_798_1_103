package com.example.demo.controller;

import com.example.demo.model.EmployeeAvailability;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.AvailabilityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/availability")
public class AvailabilityController {
    private final AvailabilityService availabilityService;
    private final EmployeeRepository employeeRepository;

    // Fixed constructor signature (image_941526.png)
    public AvailabilityController(AvailabilityService availabilityService, EmployeeRepository employeeRepository) {
        this.availabilityService = availabilityService;
        this.employeeRepository = employeeRepository;
    }

    @PostMapping
    public ResponseEntity<EmployeeAvailability> create(@RequestBody EmployeeAvailability availability) {
        return ResponseEntity.ok(availabilityService.create(availability));
    }

    // Method name changed to byDate to fix compilation error (image_941526.png)
    @GetMapping("/date/{date}")
    public ResponseEntity<List<EmployeeAvailability>> byDate(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        return ResponseEntity.ok(availabilityService.getByDate(localDate));
    }
}