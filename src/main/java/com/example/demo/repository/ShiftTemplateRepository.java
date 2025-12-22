package com.example.demo.repository;

import com.example.demo.model.ShiftTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ShiftTemplateRepository extends JpaRepository<ShiftTemplate, Long> {
    ShiftTemplate findByTemplateNameAndDepartmentId(String templateName, Long departmentId);
    List<ShiftTemplate> findByDepartmentId(Long departmentId);
}
