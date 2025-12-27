package com.example.demo.repository;

import com.example.demo.model.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
    // Correctly find availability by exact date
    List<Availability> findByDate(LocalDate date);
    
    // Check existence
    boolean existsByDateAndEmployee_Id(LocalDate date, Long employeeId);
}