package com.example.demo.service.impl;

import com.example.demo.model.Department;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.service.DepartmentService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department create(Department d) {
        // Fix for testDepartmentNameExists
        if (departmentRepository.findByName(d.getName()).isPresent()) {
            throw new RuntimeException("Department name already exists");
        }
        return departmentRepository.save(d);
    }

    @Override
    public Department get(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        departmentRepository.deleteById(id);
    }
}