package com.example.demo.service.impl;

import com.example.demo.model.ShiftTemplate;
import com.example.demo.model.Department;
import com.example.demo.repository.ShiftTemplateRepository;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.service.ShiftTemplateService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ShiftTemplateServiceImpl implements ShiftTemplateService {

    private final ShiftTemplateRepository shiftTemplateRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public ShiftTemplate saveTemplate(ShiftTemplate template) {
        // Validate department exists if one is provided
        if (template.getDepartment() != null && template.getDepartment().getId() != null) {
            Department dept = departmentRepository.findById(template.getDepartment().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Department not found"));
            template.setDepartment(dept);
        }
        return shiftTemplateRepository.save(template);
    }

    @Override
    public ShiftTemplate getTemplateById(Long id) {
        return shiftTemplateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Shift Template not found with id: " + id));
    }

    @Override
    public List<ShiftTemplate> getTemplatesByDepartment(Long departmentId) {
        // Fix for your error: "actual and formal argument lists differ in length"
        // Ensure your repository method matches this call or use Pageable.unpaged()
        return shiftTemplateRepository.findByDepartmentId(departmentId);
    }

    @Override
    public void deleteTemplate(Long id) {
        if (!shiftTemplateRepository.existsById(id)) {
            throw new EntityNotFoundException("Cannot delete. Template not found.");
        }
        shiftTemplateRepository.deleteById(id);
    }
}