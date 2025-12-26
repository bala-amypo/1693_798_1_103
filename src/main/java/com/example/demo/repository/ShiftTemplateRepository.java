package com.example.demo.repository;

import com.example.demo.model.ShiftTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ShiftTemplateRepository extends JpaRepository<ShiftTemplate, Long> {
    // Both versions to satisfy different service method calls
    List<ShiftTemplate> findByDepartmentId(Long departmentId);
    List<ShiftTemplate> findByDepartment_Id(Long departmentId);
    
    Optional<ShiftTemplate> findByTemplateNameAndDepartment_Id(String name, Long deptId);
}