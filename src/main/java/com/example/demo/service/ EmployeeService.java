package com.example.demo.service;

import com.example.demo.model.Employee;
import java.util.List;

public interface EmployeeService {
    Employee createEmployee(Employee employee); // Match Controller call
    List<Employee> getAll();                    // Match Controller call
    Employee getEmployee(Long id);               // Match Controller call
    void deleteEmployee(Long id);
}