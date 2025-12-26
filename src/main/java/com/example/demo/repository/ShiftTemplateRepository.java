package com.example.demo.repository;

import com.example.demo.model.ShiftTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ShiftTemplateRepository extends JpaRepository<ShiftTemplate, Long> {
    // This naming matches your ShiftTemplateServiceImpl
    List<ShiftTemplate> findByDepartmentId(Long departmentId);

    // ADD THIS METHOD to fix the compilation error in ScheduleServiceImpl
    List<ShiftTemplate> findByDepartment_Id(Long departmentId);
}