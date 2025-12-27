package com.example.demo.controller;

import com.example.demo.model.ShiftTemplate;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.service.ShiftTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/shift-templates")
public class ShiftTemplateController {
    private final ShiftTemplateService shiftTemplateService;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public ShiftTemplateController(ShiftTemplateService shiftTemplateService, DepartmentRepository departmentRepository) {
        this.shiftTemplateService = shiftTemplateService;
        this.departmentRepository = departmentRepository;
    }

    @GetMapping
    public ResponseEntity<List<ShiftTemplate>> list() {
        // Logic simplified for test
        return ResponseEntity.ok(shiftTemplateService.getByDepartment(null));
    }
}