package com.example.demo.service.impl;

import com.example.demo.model.ShiftTemplate;
import com.example.demo.repository.ShiftTemplateRepository;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.service.ShiftTemplateService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

@Service
public class ShiftTemplateServiceImpl implements ShiftTemplateService {
    private final ShiftTemplateRepository shiftTemplateRepository;

    public ShiftTemplateServiceImpl(ShiftTemplateRepository shiftTemplateRepository, DepartmentRepository departmentRepository) {
        this.shiftTemplateRepository = shiftTemplateRepository;
    }

    @Override
    public ShiftTemplate create(ShiftTemplate st) {
        if (st.getEndTime() != null && st.getStartTime() != null && st.getEndTime().isBefore(st.getStartTime())) {
            throw new RuntimeException("after");
        }
        
        // Fix for testShiftTemplateUniqueWithinDept
        List<ShiftTemplate> existing = shiftTemplateRepository.findByDepartmentId(st.getDepartment().getId());
        for (ShiftTemplate e : existing) {
            if (e.getStartTime().equals(st.getStartTime()) && e.getEndTime().equals(st.getEndTime())) {
                throw new RuntimeException("exists");
            }
        }
        return shiftTemplateRepository.save(st);
    }

    @Override
    public List<ShiftTemplate> getByDepartment(Long deptId) {
        List<ShiftTemplate> templates = shiftTemplateRepository.findByDepartmentId(deptId);
        return templates != null ? templates : new ArrayList<>();
    }

    @Override
    public List<ShiftTemplate> getAll() {
        return shiftTemplateRepository.findAll();
    }
    
    // Satisfy interface requirements
    @Override public ShiftTemplate saveTemplate(ShiftTemplate t) { return shiftTemplateRepository.save(t); }
    @Override public ShiftTemplate getTemplateById(Long id) { return shiftTemplateRepository.findById(id).orElse(null); }
    @Override public List<ShiftTemplate> getTemplatesByDepartment(Long id) { return getByDepartment(id); }
    @Override public void deleteTemplate(Long id) { shiftTemplateRepository.deleteById(id); }
}