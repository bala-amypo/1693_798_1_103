package com.example.demo.repository;

import com.example.demo.model.EmployeeAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AvailabilityRepository extends JpaRepository<EmployeeAvailability, Long> {
    
    // This is the specific method the compiler is looking for:
    List<EmployeeAvailability> findByEmployeeId(Long employeeId);

    // It is good practice to include these for the rest of your service logic:
    List<EmployeeAvailability> findByAvailableDate(LocalDate date);
    List<EmployeeAvailability> findByAvailableDateAndAvailable(LocalDate date, boolean available);
    Optional<EmployeeAvailability> findByEmployee_IdAndAvailableDate(Long employeeId, LocalDate date);
}