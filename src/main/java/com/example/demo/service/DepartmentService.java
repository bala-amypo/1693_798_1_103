package com.example.demo.service;

import com.example.demo.model.Department;
import java.util.List;

public interface DepartmentService {
    Department create(Department department);
    Department get(Long id);
    List<Department> getAll(); // Required by Test line 708
    void delete(Long id);
}