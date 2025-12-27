package com.example.demo.service;

import com.example.demo.model.ShiftTemplate;
import java.util.List;

public interface ShiftTemplateService {
    ShiftTemplate saveShiftTemplate(ShiftTemplate shiftTemplate);
    List<ShiftTemplate> getAll(); // Matches Controller call
    ShiftTemplate getShiftTemplateById(Long id);
    void deleteShiftTemplate(Long id);
}