package com.example.demo.repository;

import com.example.demo.model.ShiftTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ShiftTemplateRepository extends JpaRepository<ShiftTemplate, Long> {
    // Ensure this method matches the naming convention used in your Service
    List<ShiftTemplate> findByDepartmentId(Long departmentId);
}