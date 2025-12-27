package com.example.demo.controller;

import com.example.demo.model.Availability;
import com.example.demo.service.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/availability")
public class AvailabilityController {

    @Autowired
    private AvailabilityService availabilityService;

    @PostMapping
    public Availability createAvailability(@RequestBody Availability availability) {
        return availabilityService.create(availability);
    }

    @GetMapping
    public List<Availability> getAvailability(
            @RequestParam(required = false) 
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return availabilityService.getAvailability(date);
    }

    @GetMapping("/employee/{employeeId}")
    public List<Availability> getByEmployee(@PathVariable Long employeeId) {
        return availabilityService.getByEmployee(employeeId);
    }

    @DeleteMapping("/{id}")
    public void deleteAvailability(@PathVariable Long id) {
        availabilityService.delete(id);
    }
}