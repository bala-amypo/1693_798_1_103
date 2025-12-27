package com.example.demo.service.impl;

import com.example.demo.model.ShiftTemplate;
import com.example.demo.repository.ShiftTemplateRepository;
import com.example.demo.service.ShiftTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.util.List;

@Service
public class ShiftTemplateServiceImpl implements ShiftTemplateService {

    @Autowired
    private ShiftTemplateRepository shiftTemplateRepository;

    @Override
    public ShiftTemplate saveShiftTemplate(ShiftTemplate st) {
        // Fix for: testShiftTemplateInvalidTime
        if (st.getStartTime() == null || st.getEndTime() == null || 
            st.getEndTime().isBefore(st.getStartTime())) {
            throw new IllegalArgumentException("Invalid shift times");
        }
        
        // Fix for: testShiftTemplateUniqueWithinDept
        boolean exists = shiftTemplateRepository.existsByTemplateNameAndDepartment(
            st.getTemplateName(), st.getDepartment());
        if (exists) {
            throw new RuntimeException("Template name already exists in this department");
        }
        
        return shiftTemplateRepository.save(st);
    }

    @Override
    public List<ShiftTemplate> getAllShiftTemplates() {
        return shiftTemplateRepository.findAll();
    }

    @Override
    public ShiftTemplate getShiftTemplateById(Long id) {
        return shiftTemplateRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteShiftTemplate(Long id) {
        shiftTemplateRepository.deleteById(id);
    }
}