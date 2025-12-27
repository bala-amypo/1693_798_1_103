package com.example.demo.service.impl;

import com.example.demo.model.Department;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.service.DepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department create(Department department) {
        if (departmentRepository.existsByName(department.getName())) {
            throw new RuntimeException("Department exists");
        }
        return departmentRepository.save(department);
    }

    @Override
    public Department get(Long id) {
        return departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Department not found"));
    }

    @Override
    public void delete(Long id) {
        if (!departmentRepository.existsById(id)) throw new RuntimeException("Department not found");
        departmentRepository.deleteById(id);
    }

    @Override
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }
}