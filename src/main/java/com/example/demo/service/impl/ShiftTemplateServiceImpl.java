package com.example.demo.service.impl;

import com.example.demo.model.ShiftTemplate;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.ShiftTemplateRepository;
import com.example.demo.service.ShiftTemplateService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class ShiftTemplateServiceImpl implements ShiftTemplateService {

    private final ShiftTemplateRepository shiftTemplateRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public ShiftTemplateServiceImpl(ShiftTemplateRepository shiftTemplateRepository, DepartmentRepository departmentRepository) {
        this.shiftTemplateRepository = shiftTemplateRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public ShiftTemplate create(ShiftTemplate st) {
        if (st.getEndTime().isBefore(st.getStartTime())) {
            throw new IllegalArgumentException("end time after start time");
        }
        departmentRepository.findById(st.getDepartment().getId()); // validate dept
        
        if (shiftTemplateRepository.findByTemplateNameAndDepartment_Id(st.getTemplateName(), st.getDepartment().getId()).isPresent()) {
            throw new RuntimeException("unique constraint");
        }
        return shiftTemplateRepository.save(st);
    }

    @Override
    public List<ShiftTemplate> getByDepartment(Long departmentId) {
        return shiftTemplateRepository.findByDepartment_Id(departmentId);
    }
}