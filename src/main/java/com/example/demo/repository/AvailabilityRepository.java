package com.example.demo.repository;
import com.example.demo.model.EmployeeAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AvailabilityRepository extends JpaRepository<EmployeeAvailability, Long> {
    List<EmployeeAvailability> findByAvailableDateAndAvailable(LocalDate date, boolean available);
    List<EmployeeAvailability> findByEmployee_Id(Long employeeId);
    Optional<EmployeeAvailability> findByEmployee_IdAndAvailableDate(Long empId, LocalDate date);
}