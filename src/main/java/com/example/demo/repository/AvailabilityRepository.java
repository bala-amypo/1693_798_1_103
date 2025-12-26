package com.example.demo.repository;

import com.example.demo.model.EmployeeAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AvailabilityRepository extends JpaRepository<EmployeeAvailability, Long> {
    
    // FIX: This specific method is required by your Service Implementation
    List<EmployeeAvailability> findByEmployeeId(Long employeeId);

    // Required by MasterTestNGSuiteTest (using underscore for nested property ID)
    List<EmployeeAvailability> findByEmployee_Id(Long employeeId);
    
    // Required for scheduling and date-based lookups
    List<EmployeeAvailability> findByAvailableDate(LocalDate date);
    
    List<EmployeeAvailability> findByAvailableDateAndAvailable(LocalDate date, boolean available);
    
    Optional<EmployeeAvailability> findByEmployee_IdAndAvailableDate(Long employeeId, LocalDate date);
}