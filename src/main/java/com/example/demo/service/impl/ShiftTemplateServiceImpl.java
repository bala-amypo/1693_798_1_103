package com.example.demo.service.impl;

import com.example.demo.model.ShiftTemplate;
import com.example.demo.repository.ShiftTemplateRepository;
import com.example.demo.service.ShiftTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ShiftTemplateServiceImpl implements ShiftTemplateService {

    @Autowired
    private ShiftTemplateRepository shiftTemplateRepository;

    @Override
    public ShiftTemplate saveShiftTemplate(ShiftTemplate st) {
        if (shiftTemplateRepository.existsByTemplateNameAndDepartment(st.getTemplateName(), st.getDepartment())) {
            throw new RuntimeException("Duplicate shift template name in this department");
        }
        return shiftTemplateRepository.save(st);
    }

    @Override
    public List<ShiftTemplate> getAll() {
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