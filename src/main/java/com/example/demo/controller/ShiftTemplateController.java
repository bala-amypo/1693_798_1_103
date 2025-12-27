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
    
    // The test passes a DepartmentRepository to the constructor, so we must accept it here
    // even if we don't strictly use it in the controller logic.
    private final DepartmentRepository departmentRepository;

    // --- FIX 1: Add the 2-argument Constructor expected by the Test ---
    @Autowired
    public ShiftTemplateController(ShiftTemplateService shiftTemplateService, 
                                   DepartmentRepository departmentRepository) {
        this.shiftTemplateService = shiftTemplateService;
        this.departmentRepository = departmentRepository;
    }

    @GetMapping
    public ResponseEntity<List<ShiftTemplate>> getAllTemplates() {
        return ResponseEntity.ok(shiftTemplateService.getAll());
    }

    @PostMapping
    public ResponseEntity<ShiftTemplate> createTemplate(@RequestBody ShiftTemplate template) {
        return ResponseEntity.ok(shiftTemplateService.saveShiftTemplate(template));
    }

    // --- FIX 2: Add 'list()' method expected by the Test ---
    // This acts as an alias to your main get method
    public ResponseEntity<List<ShiftTemplate>> list() {
        return getAllTemplates();
    }
}