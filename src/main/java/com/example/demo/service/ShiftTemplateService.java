package com.example.demo.service;

import com.example.demo.model.ShiftTemplate;
import java.util.List;

public interface ShiftTemplateService {
    ShiftTemplate saveTemplate(ShiftTemplate template);
    ShiftTemplate getTemplateById(Long id);
    List<ShiftTemplate> getTemplatesByDepartment(Long departmentId);
    void deleteTemplate(Long id);
    List<ShiftTemplate> getAll();
    // Supporting method for the Master Test suite
    ShiftTemplate create(ShiftTemplate st);
    List<ShiftTemplate> getByDepartment(Long deptId);
}