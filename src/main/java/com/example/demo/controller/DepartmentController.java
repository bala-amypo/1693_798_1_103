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
        return ResponseEntity.ok(departmentService.create(department));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> get(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.get(id));
    }

    @GetMapping
    public ResponseEntity<List<Department>> list() {
        return ResponseEntity.ok(departmentService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        departmentService.delete(id);
        return ResponseEntity.ok("Deleted");
    }
}