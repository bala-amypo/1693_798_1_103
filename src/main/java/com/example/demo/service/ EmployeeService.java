package com.example.demo.service;

import com.example.demo.model.Employee;
import java.util.List;

public interface EmployeeService {

    /**
     * Saves or updates an employee.
     */
    Employee saveEmployee(Employee employee);

    /**
     * Retrieves an employee by their ID.
     */
    Employee getEmployeeById(Long id);

    /**
     * Retrieves all employees.
     */
    List<Employee> getAllEmployees();

    /**
     * Retrieves all employees belonging to a specific department.
     */
    List<Employee> getEmployeesByDepartment(Long departmentId);

    /**
     * Deletes an employee by their ID.
     */
    void deleteEmployee(Long id);
}