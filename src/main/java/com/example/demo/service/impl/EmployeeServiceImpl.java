package com.example.demo.service.impl;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        // Fix for testCreateEmployeeEmailDuplicate
        if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Long id, Employee details) {
        // Fix for testUpdateEmployee: ensure it exists first
        return employeeRepository.findById(id).map(employee -> {
            employee.setName(details.getName());
            employee.setEmail(details.getEmail());
            employee.setRole(details.getRole());
            employee.setMaxHoursPerWeek(details.getMaxHoursPerWeek());
            return employeeRepository.save(employee);
        }).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    @Override
    public void deleteEmployee(Long id) {
        // Fix for testDeleteEmployee: check existence before delete to avoid raw JPA errors
        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException("Employee not found");
        }
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> getAll() { return employeeRepository.findAll(); }

    @Override
    public Employee getEmployee(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
    }
}