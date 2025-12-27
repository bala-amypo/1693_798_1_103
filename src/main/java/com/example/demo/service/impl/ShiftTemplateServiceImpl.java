package com.example.demo.service.impl;

import com.example.demo.model.ShiftTemplate;
import com.example.demo.repository.ShiftTemplateRepository;
import com.example.demo.repository.DepartmentRepository; // Added for constructor compatibility
import com.example.demo.service.ShiftTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ShiftTemplateServiceImpl implements ShiftTemplateService {

    private final ShiftTemplateRepository shiftTemplateRepository;
    // We include this because the test tries to inject it, even if we don't use it heavily
    private final DepartmentRepository departmentRepository;

    // --- CONSTRUCTOR INJECTION (Fixes Test Instantiation Error) ---
    @Autowired
    public ShiftTemplateServiceImpl(ShiftTemplateRepository shiftTemplateRepository, 
                                    DepartmentRepository departmentRepository) {
        this.shiftTemplateRepository = shiftTemplateRepository;
        this.departmentRepository = departmentRepository;
    }

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

    // --- COMPATIBILITY IMPLEMENTATIONS FOR TESTS ---

    @Override
    public ShiftTemplate create(ShiftTemplate shiftTemplate) {
        return saveShiftTemplate(shiftTemplate);
    }

    @Override
    public List<ShiftTemplate> list() {
        return getAll();
    }

    @Override
    public List<ShiftTemplate> getByDepartment(long deptId) {
        return shiftTemplateRepository.findByDepartment_Id(deptId);
    }
}