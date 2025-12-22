package com.example.demo.controller;

import com.example.demo.model.EmployeeAvailability;
import com.example.demo.service.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/availability")
public class AvailabilityController {

    @Autowired
    private AvailabilityService availabilityService;

    @PostMapping("/employee/{employeeId}")
    public ResponseEntity<EmployeeAvailability> createAvailability(@PathVariable Long employeeId,
                                                                 @RequestBody EmployeeAvailability availability) {
        // Service handles employee lookup internally - NO setEmployeeId needed
        return ResponseEntity.ok(availabilityService.createEmployeeAvailability(availability));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<EmployeeAvailability>> getEmployeeAvailability(@PathVariable Long employeeId) {
        return ResponseEntity.ok(availabilityService.getByEmployee(employeeId));
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<EmployeeAvailability>> getAvailabilityByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(availabilityService.getByDate(date));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeAvailability> updateAvailability(@PathVariable Long id, 
                                                                 @RequestBody EmployeeAvailability availability) {
        return ResponseEntity.ok(availabilityService.update(id, availability));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAvailability(@PathVariable Long id) {
        availabilityService.delete(id);
        return ResponseEntity.ok().build();
    }
}
