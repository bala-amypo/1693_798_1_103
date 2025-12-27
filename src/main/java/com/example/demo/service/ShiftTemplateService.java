package com.example.demo.service;

import com.example.demo.model.ShiftTemplate;
import java.util.List;

public interface ShiftTemplateService {
    ShiftTemplate saveShiftTemplate(ShiftTemplate shiftTemplate);
    List<ShiftTemplate> getAllShiftTemplates();
    ShiftTemplate getShiftTemplateById(Long id);
    void deleteShiftTemplate(Long id);
    // Add any other methods your controller calls here
}