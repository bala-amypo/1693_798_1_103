package com.example.demo.service.impl;

import com.example.demo.model.Department;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.service.DepartmentService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    public DepartmentServiceImpl(DepartmentRepository repo) { this.departmentRepository = repo; }

    @Override
    public Department create(Department d) {
        if (departmentRepository.existsByName(d.getName())) {
            throw new RuntimeException("exists");
        }
        return departmentRepository.save(d);
    }

    @Override
    public Department get(Long id) { return departmentRepository.findById(id).orElse(null); }
    @Override
    public List<Department> getAll() { return departmentRepository.findAll(); }

    @Override
    public void delete(Long id) {
        Department dept = departmentRepository.findById(id).orElse(null);
        if (dept != null) {
            departmentRepository.delete(dept);
        }
    }
}