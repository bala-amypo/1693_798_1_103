package com.example.demo.controller;

import com.example.demo.model.Department;
import com.example.demo.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    private final DepartmentService departmentService;
    public DepartmentController(DepartmentService ds) { this.departmentService = ds; }

    @GetMapping("/{id}")
    public ResponseEntity<Department> get(@PathVariable Long id) {
        Department d = departmentService.get(id);
        return d != null ? ResponseEntity.ok(d) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if (departmentService.get(id) == null) {
            return ResponseEntity.notFound().build();
        }
        departmentService.delete(id);
        return ResponseEntity.ok("Deleted"); // Test expects "Deleted"
    }
}