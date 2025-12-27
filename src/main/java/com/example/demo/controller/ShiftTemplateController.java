package com.example.demo.controller;

import com.example.demo.model.ShiftTemplate;
import com.example.demo.service.ShiftTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shift-templates")
public class ShiftTemplateController {

    @Autowired
    private ShiftTemplateService shiftTemplateService;

    @GetMapping
    public ResponseEntity<List<ShiftTemplate>> getAllTemplates() {
        // Now matches the getAll() method in the Service
        return ResponseEntity.ok(shiftTemplateService.getAll());
    }

    @PostMapping
    public ResponseEntity<ShiftTemplate> createTemplate(@RequestBody ShiftTemplate template) {
        return ResponseEntity.ok(shiftTemplateService.saveShiftTemplate(template));
    }
}