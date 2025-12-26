package com.example.demo.service;
import com.example.demo.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface EmployeeService {
    Employee createEmployee(Employee e);
    Employee getEmployee(Long id);
    Employee updateEmployee(Long id, Employee e);
    void deleteEmployee(Long id);
    Employee findByEmail(String email);
    List<Employee> getAll();
    Page<Employee> getActiveEmployees(Pageable pageable);
}