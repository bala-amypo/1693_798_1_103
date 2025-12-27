package com.example.demo.repository;

import com.example.demo.model.ShiftTemplate;
import com.example.demo.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShiftTemplateRepository extends JpaRepository<ShiftTemplate, Long> {
    
    // Used by your Service
    boolean existsByTemplateNameAndDepartment(String templateName, Department department);
    
    // Used by your Service
    List<ShiftTemplate> findByDepartment_Id(Long departmentId);

    // --- COMPATIBILITY FOR TESTS ---
    // The test expects this exact name:
    Optional<ShiftTemplate> findByTemplateNameAndDepartment_Id(String name, Long deptId);
}