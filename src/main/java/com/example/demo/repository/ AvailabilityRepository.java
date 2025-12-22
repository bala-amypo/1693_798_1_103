package com.example.demo.repository;

import com.example.demo.model.EmployeeAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvailabilityRepository extends JpaRepository<EmployeeAvailability, Long> {

    /**
     * Finds all availability records for a specific employee.
     * Matches the call in AvailabilityServiceImpl: findByEmployeeId(Long id)
     */
    List<EmployeeAvailability> findByEmployeeId(Long employeeId);
}