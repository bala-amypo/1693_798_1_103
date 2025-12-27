package com.example.demo.repository;

import com.example.demo.model.ShiftTemplate;
import com.example.demo.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ShiftTemplateRepository extends JpaRepository<ShiftTemplate, Long> {

    // 1. Fixes the "existsBy..." error from the previous step
    boolean existsByTemplateNameAndDepartment(String templateName, Department department);

    // 2. Fixes the NEW error: findByDepartment_Id
    // Spring automatically converts this to: SELECT * FROM shift_template WHERE department_id = ?
    List<ShiftTemplate> findByDepartment_Id(Long departmentId);
}