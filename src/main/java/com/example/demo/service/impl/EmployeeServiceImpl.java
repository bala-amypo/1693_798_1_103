package com.example.demo.service.impl;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Employee createEmployee(Employee e) {
        if (repository.existsByEmail(e.getEmail())) {
            throw new RuntimeException("exists");
        }
        
        // Keeps testRoleDefaultToStaff passing
        if (e.getRole() == null || e.getRole().isEmpty()) {
            e.setRole("STAFF");
        }
        
        // Keeps testEmployeeMaxHoursInvalid passing
        if (e.getMaxHoursPerWeek() != null && (e.getMaxHoursPerWeek() < 0 || e.getMaxHoursPerWeek() > 168)) {
            throw new RuntimeException("invalid hours");
        }
        
        return repository.save(e);
    }

    @Override
    public Employee getEmployee(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Employee updateEmployee(Long id, Employee e) {
        Employee old = repository.findById(id).orElseThrow(() -> new RuntimeException("not found"));
        old.setFullName(e.getFullName());
        old.setMaxHoursPerWeek(e.getMaxHoursPerWeek());
        return repository.save(old);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee emp = repository.findById(id).orElse(null);
        if (emp != null) {
            repository.delete(emp);
        }
    }

    @Override
    public Employee findByEmail(String email) {
        return repository.findByEmail(email).orElse(null);
    }

    @Override
    public List<Employee> getAll() {
        return repository.findAll();
    }

    @Override
    public Page<Employee> getActiveEmployees(Pageable pageable) {
        return repository.findAll(pageable);
    }
}