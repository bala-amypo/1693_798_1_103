package com.example.demo.repository;

import com.example.demo.model.EmployeeAvailability;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AvailabilityRepository extends JpaRepository<EmployeeAvailability, Long> {

    // Single availability lookup (use Optional for safety)
    Optional<EmployeeAvailability> findByEmployeeIdAndAvailableDate(Long employeeId, LocalDate availableDate);

    // Find available employees on specific date
    List<EmployeeAvailability> findByAvailableDateAndAvailable(LocalDate date, boolean available);

    // All availability for employee
    List<EmployeeAvailability> findByEmployeeIdOrderByAvailableDateDesc(Long employeeId);

    // Active employees available on date (with pagination)
    Page<EmployeeAvailability> findByAvailableDateAndAvailableOrderByEmployeeId(
            LocalDate date, boolean available, Pageable pageable);

    // Custom query: Employees available in date range
    @Query("SELECT a FROM EmployeeAvailability a WHERE a.employee.id = :employeeId " +
           "AND a.availableDate BETWEEN :startDate AND :endDate " +
           "AND a.available = true")
    List<EmployeeAvailability> findAvailableByEmployeeIdAndDateRange(
            @Param("employeeId") Long employeeId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    // Count available employees on date
    @Query("SELECT COUNT(a) FROM EmployeeAvailability a WHERE a.availableDate = :date AND a.available = true")
    long countAvailableEmployeesOnDate(@Param("date") LocalDate date);

    // Find by multiple dates
    List<EmployeeAvailability> findByAvailableDateInAndAvailable(List<LocalDate> dates, boolean available);

    // Delete all for employee (useful for bulk updates)
    void deleteByEmployeeId(Long employeeId);

    // Check if exists
    boolean existsByEmployeeIdAndAvailableDate(Long employeeId, LocalDate availableDate);
}
