package com.example.demo.service;

import com.example.demo.model.Employee;
import java.util.List;

public interface EmployeeService {
    Employee createEmployee(Employee employee);
    List<Employee> getAll();
    Employee getEmployee(Long id);
    Employee updateEmployee(Long id, Employee details);
    void deleteEmployee(Long id);
    
    // Returns Employee directly (not Optional) to satisfy test line 292
    Employee findByEmail(String email);
}