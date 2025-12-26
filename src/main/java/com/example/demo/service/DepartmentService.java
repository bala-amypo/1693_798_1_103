package com.example.demo.service;

import com.example.demo.model.Department;

public interface DepartmentService {
    Department create(Department d);
    Department get(Long id);
    void delete(Long id);
}