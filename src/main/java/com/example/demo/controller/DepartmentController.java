package com.example.demo.controller;

import com.example.demo.model.Department;
import com.example.demo.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> get(@PathVariable Long id) {
        Department d = departmentService.get(id);
        return d != null ? ResponseEntity.ok(d) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        // Required for testDeleteMissingDepartment
        if (departmentService.get(id) == null) {
            return ResponseEntity.notFound().build();
        }
        departmentService.delete(id);
        return ResponseEntity.ok().build();
    }
}