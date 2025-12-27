package com.example.demo.service;

import com.example.demo.model.ShiftTemplate;
import java.util.List;

public interface ShiftTemplateService {
    
    // Standard methods
    ShiftTemplate saveShiftTemplate(ShiftTemplate shiftTemplate);
    List<ShiftTemplate> getAll();
    ShiftTemplate getShiftTemplateById(Long id);
    void deleteShiftTemplate(Long id);

    // --- COMPATIBILITY METHODS FOR TESTS ---
    ShiftTemplate create(ShiftTemplate shiftTemplate); // Alias for save
    List<ShiftTemplate> list(); // Alias for getAll
    List<ShiftTemplate> getByDepartment(long deptId); // New requirement from test
}