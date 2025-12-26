package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> list() { // Required by Test line 490
        return employeeService.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) { // Required by Test line 671
        employeeService.deleteEmployee(id);
    }
}