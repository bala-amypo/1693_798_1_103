package com.example.demo.repository;

import com.example.demo.model.EmployeeAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AvailabilityRepository extends JpaRepository<EmployeeAvailability, Long> {
    List<EmployeeAvailability> findByAvailableDate(LocalDate date);
    List<EmployeeAvailability> findByEmployeeId(Long employeeId);
    List<EmployeeAvailability> findByEmployee_Id(Long employeeId);
    Optional<EmployeeAvailability> findByEmployee_IdAndAvailableDate(Long employeeId, LocalDate date);
    List<EmployeeAvailability> findByAvailableDateAndAvailable(LocalDate date, boolean available);
}