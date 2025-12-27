package com.example.demo.repository;

import com.example.demo.model.ShiftTemplate;
import com.example.demo.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShiftTemplateRepository extends JpaRepository<ShiftTemplate, Long> {
    // Fixes the existsByTemplateNameAndDepartment error
    boolean existsByTemplateNameAndDepartment(String templateName, Department department);
}