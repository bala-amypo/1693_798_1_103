package com.example.demo.service;

import com.example.demo.model.Employee;
import java.util.List;

public interface EmployeeService {
    Employee createEmployee(Employee employee);
    List<Employee> getAll();
    Employee getEmployee(Long id);
    void deleteEmployee(Long id);
}