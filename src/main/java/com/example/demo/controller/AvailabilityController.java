package com.example.demo.controller;

import com.example.demo.model.EmployeeAvailability;
import com.example.demo.service.AvailabilityService;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/availability")
public class AvailabilityController {
    private final AvailabilityService availabilityService;

    public AvailabilityController(AvailabilityService availabilityService, EmployeeRepository employeeRepository) {
        this.availabilityService = availabilityService;
    }

    @GetMapping("/date")
    public ResponseEntity<List<EmployeeAvailability>> byDate(@RequestParam String date) {
        return ResponseEntity.ok(availabilityService.getByDate(LocalDate.parse(date)));
    }
}