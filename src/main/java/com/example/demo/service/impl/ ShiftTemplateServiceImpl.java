package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Department;
import com.example.demo.model.ShiftTemplate;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.ShiftTemplateRepository;
import com.example.demo.service.ShiftTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ShiftTemplateServiceImpl implements ShiftTemplateService {

    @Autowired
    private ShiftTemplateRepository shiftTemplateRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public ShiftTemplate createShiftTemplate(ShiftTemplate template) {
        if (template.getEndTime().isBefore(template.getStartTime())) {
            throw new IllegalArgumentException("End time must be after start time");
        }

        Department department = departmentRepository.findById(template.getDepartment().getId())
            .orElseThrow(() -> new ResourceNotFoundException("Department not found"));

        if (shiftTemplateRepository.findByTemplateNameAndDepartmentId(template.getTemplateName(), department.getId()) != null) {
            throw new IllegalArgumentException("Shift template name already exists in this department");
        }

        template.setDepartment(department);
        return shiftTemplateRepository.save(template);
    }

    @Override
    public List<ShiftTemplate> getByDepartment(Long departmentId) {
        return shiftTemplateRepository.findByDepartmentId(departmentId);
    }
}
