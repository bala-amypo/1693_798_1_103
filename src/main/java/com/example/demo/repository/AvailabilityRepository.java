package com.example.demo.repository;

import com.example.demo.model.EmployeeAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AvailabilityRepository extends JpaRepository<EmployeeAvailability, Long> {
    List<EmployeeAvailability> findByAvailableDate(LocalDate date);
    
    // Support both underscore and standard naming to satisfy all service calls
    List<EmployeeAvailability> findByEmployeeId(Long employeeId);
    List<EmployeeAvailability> findByEmployee_Id(Long employeeId);
    
    // Required for the unique check logic in AvailabilityServiceImpl
    Optional<EmployeeAvailability> findByEmployee_IdAndAvailableDate(Long employeeId, LocalDate date);
    
    // Required for ScheduleServiceImpl logic
    List<EmployeeAvailability> findByAvailableDateAndAvailable(LocalDate date, boolean available);
}