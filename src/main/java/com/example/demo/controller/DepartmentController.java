// package com.example.demo.controller;

// import com.example.demo.model.Department;
// import com.example.demo.service.DepartmentService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/api/departments")
// public class DepartmentController {
//     private final DepartmentService departmentService;

//     @Autowired
//     public DepartmentController(DepartmentService departmentService) {
//         this.departmentService = departmentService;
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<Department> get(@PathVariable Long id) {
//         return ResponseEntity.ok(departmentService.get(id));
//     }

//     @DeleteMapping("/{id}")
//     public ResponseEntity<String> delete(@PathVariable Long id) {
//         departmentService.delete(id);
//         return ResponseEntity.ok("Deleted");
//     }
// }

package com.example.demo.controller;

import com.example.demo.model.Department;
import com.example.demo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<Department> create(@RequestBody Department department) {
        try {
            return ResponseEntity.ok(departmentService.create(department));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> get(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(departmentService.get(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping
    public ResponseEntity<List<Department>> list() {
        return ResponseEntity.ok(departmentService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        // Fix: Catch the RuntimeException locally so unit tests invoking this method directly
        // receive a 404 response instead of a crash.
        try {
            departmentService.delete(id);
            return ResponseEntity.ok("Deleted");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}