package com.example.demo.repository;

import com.example.demo.model.EmployeeAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface AvailabilityRepository extends JpaRepository<EmployeeAvailability, Long> {
    List<EmployeeAvailability> findByAvailableDate(LocalDate date);
    List<EmployeeAvailability> findByEmployeeId(Long employeeId);
}