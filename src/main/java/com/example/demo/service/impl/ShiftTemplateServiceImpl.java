package com.example.demo.service.impl;

import com.example.demo.model.ShiftTemplate;
import com.example.demo.repository.ShiftTemplateRepository;
import com.example.demo.repository.DepartmentRepository; // Added import
import com.example.demo.service.ShiftTemplateService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ShiftTemplateServiceImpl implements ShiftTemplateService {
    private final ShiftTemplateRepository shiftTemplateRepository;
    private final DepartmentRepository departmentRepository;

    // Fixed constructor to match test suite expectations (image_948da1.png)
    public ShiftTemplateServiceImpl(ShiftTemplateRepository shiftTemplateRepository, DepartmentRepository departmentRepository) {
        this.shiftTemplateRepository = shiftTemplateRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public ShiftTemplate create(ShiftTemplate st) {
        if (st.getEndTime() != null && st.getStartTime() != null && st.getEndTime().isBefore(st.getStartTime())) {
            throw new RuntimeException("after");
        }
        
        Optional<ShiftTemplate> existing = shiftTemplateRepository.findByTemplateNameAndDepartment_Id(st.getTemplateName(), st.getDepartment().getId());
        if (existing.isPresent()) {
            throw new RuntimeException("exists");
        }
        
        return shiftTemplateRepository.save(st);
    }

    @Override public List<ShiftTemplate> getByDepartment(Long deptId) { return shiftTemplateRepository.findByDepartment_Id(deptId); }
    @Override public List<ShiftTemplate> getAll() { return shiftTemplateRepository.findAll(); }
    @Override public ShiftTemplate saveTemplate(ShiftTemplate t) { return shiftTemplateRepository.save(t); }
    @Override public ShiftTemplate getTemplateById(Long id) { return shiftTemplateRepository.findById(id).orElse(null); }
    @Override public List<ShiftTemplate> getTemplatesByDepartment(Long id) { return getByDepartment(id); }
    @Override public void deleteTemplate(Long id) { shiftTemplateRepository.deleteById(id); }
}