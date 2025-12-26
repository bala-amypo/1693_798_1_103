package com.example.demo.repository;

import com.example.demo.model.ShiftTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ShiftTemplateRepository extends JpaRepository<ShiftTemplate, Long> {
    List<ShiftTemplate> findByDepartmentId(Long departmentId);
    List<ShiftTemplate> findByDepartment_Id(Long departmentId);
    
    // Required to fix "cannot find symbol" error in MasterTestNGSuiteTest.java:[170,37]
    Optional<ShiftTemplate> findByTemplateNameAndDepartment_Id(String name, Long departmentId);
}