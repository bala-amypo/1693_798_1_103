package com.example.demo.service;

import com.example.demo.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {
    Page<Employee> getActiveEmployees(Pageable pageable);
}
