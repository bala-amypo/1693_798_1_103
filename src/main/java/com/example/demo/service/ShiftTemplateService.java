package com.example.demo.service;

import com.example.demo.model.ShiftTemplate;
import java.util.List;

public interface ShiftTemplateService {
    ShiftTemplate saveTemplate(ShiftTemplate template);
    ShiftTemplate getTemplateById(Long id);
    List<ShiftTemplate> getTemplatesByDepartment(Long departmentId);
    void deleteTemplate(Long id);
    List<ShiftTemplate> getAll();
}