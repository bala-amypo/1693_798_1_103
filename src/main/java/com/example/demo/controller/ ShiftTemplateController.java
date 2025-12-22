package com.example.demo.controller;

import com.example.demo.model.ShiftTemplate;
import com.example.demo.service.ShiftTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shift-templates")
@RequiredArgsConstructor // Automatically creates constructor for ShiftTemplateService
public class ShiftTemplateController {

    private final ShiftTemplateService shiftTemplateService;

    @PostMapping
    public ResponseEntity<ShiftTemplate> createTemplate(@RequestBody ShiftTemplate template) {
        return ResponseEntity.ok(shiftTemplateService.saveTemplate(template));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShiftTemplate> getTemplateById(@PathVariable Long id) {
        return ResponseEntity.ok(shiftTemplateService.getTemplateById(id));
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<ShiftTemplate>> getTemplatesByDepartment(@PathVariable Long departmentId) {
        // Note: Ensure your service method matches this call
        return ResponseEntity.ok(shiftTemplateService.getTemplatesByDepartment(departmentId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTemplate(@PathVariable Long id) {
        shiftTemplateService.deleteTemplate(id);
        return ResponseEntity.noContent().build();
    }
}