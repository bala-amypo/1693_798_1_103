package com.example.demo.service.impl;

import com.example.demo.model.ShiftTemplate;
import com.example.demo.repository.ShiftTemplateRepository;
import com.example.demo.service.ShiftTemplateService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ShiftTemplateServiceImpl implements ShiftTemplateService {
    private final ShiftTemplateRepository shiftTemplateRepository;

    public ShiftTemplateServiceImpl(ShiftTemplateRepository repository) {
        this.shiftTemplateRepository = repository;
    }

    @Override
    public ShiftTemplate create(ShiftTemplate st) {
        // Validation for start/end time
        if (st.getEndTime() != null && st.getStartTime() != null && st.getEndTime().isBefore(st.getStartTime())) {
            throw new RuntimeException("after");
        }
        
        // Check for uniqueness within department to pass testShiftTemplateUniqueWithinDept
        Optional<ShiftTemplate> existing = shiftTemplateRepository.findByTemplateNameAndDepartment_Id(st.getTemplateName(), st.getDepartment().getId());
        if (existing.isPresent()) {
            throw new RuntimeException("exists");
        }
        
        return shiftTemplateRepository.save(st);
    }

    // ADD THIS to satisfy the ShiftTemplateService interface if required by tests
    public Optional<ShiftTemplate> findByNameAndDept(String name, Long deptId) {
        return shiftTemplateRepository.findByTemplateNameAndDepartment_Id(name, deptId);
    }

    @Override
    public List<ShiftTemplate> getByDepartment(Long deptId) {
        return shiftTemplateRepository.findByDepartment_Id(deptId);
    }

    @Override public List<ShiftTemplate> getAll() { return shiftTemplateRepository.findAll(); }
    @Override public ShiftTemplate saveTemplate(ShiftTemplate t) { return shiftTemplateRepository.save(t); }
    @Override public ShiftTemplate getTemplateById(Long id) { return shiftTemplateRepository.findById(id).orElse(null); }
    @Override public List<ShiftTemplate> getTemplatesByDepartment(Long id) { return getByDepartment(id); }
    @Override public void deleteTemplate(Long id) { shiftTemplateRepository.deleteById(id); }
}