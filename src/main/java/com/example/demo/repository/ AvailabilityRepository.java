package com.example.demo.repository;

import com.example.demo.model.EmployeeAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface AvailabilityRepository extends JpaRepository<EmployeeAvailability, Long> {
    EmployeeAvailability findByEmployeeIdAndAvailableDate(Long employeeId, LocalDate availableDate);
    List<EmployeeAvailability> findByAvailableDateAndAvailable(LocalDate date, boolean available);
    List<EmployeeAvailability> findByEmployeeId(Long employeeId);
}
