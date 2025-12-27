package com.example.demo.repository;

import com.example.demo.model.EmployeeAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AvailabilityRepository extends JpaRepository<EmployeeAvailability, Long> {
    // Exact method name used in Test: testAvailabilityRepoQuery
    List<EmployeeAvailability> findByAvailableDateAndAvailable(LocalDate date, Boolean available);
    
    // Exact method name used in Test: testAvailabilityUniqueCheck
    Optional<EmployeeAvailability> findByEmployee_IdAndAvailableDate(Long empId, LocalDate date);

    // Exact method name used in Test: testAvailabilityRepoEmployeeQuery
    List<EmployeeAvailability> findByEmployee_Id(Long empId);
}