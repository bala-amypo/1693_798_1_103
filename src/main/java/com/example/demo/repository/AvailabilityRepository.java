package com.example.demo.repository;

import com.example.demo.model.EmployeeAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AvailabilityRepository extends JpaRepository<EmployeeAvailability, Long> {
    // Required by Test line 241
    Optional<EmployeeAvailability> findByEmployee_IdAndAvailableDate(Long employeeId, LocalDate date);
    
    // Required by Test line 638 & 639
    List<EmployeeAvailability> findByEmployee_Id(Long employeeId);
    
    List<EmployeeAvailability> findByAvailableDate(LocalDate date);
    
    List<EmployeeAvailability> findByAvailableDateAndAvailable(LocalDate date, boolean available);
}