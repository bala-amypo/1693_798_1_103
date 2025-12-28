package com.example.demo.service;

import com.example.demo.model.Employee;
import java.util.List;
import java.util.Optional; // Add this import

public interface EmployeeService {
    Employee createEmployee(Employee employee);
    List<Employee> getAll();
    Employee getEmployee(Long id);
    Employee updateEmployee(Long id, Employee details);
    void deleteEmployee(Long id);
    
    // Change this to return Optional<Employee>
    Optional<Employee> findByEmail(String email); 
}