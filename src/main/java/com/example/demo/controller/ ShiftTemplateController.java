package com.example.demo.controller;

import com.example.demo.model.ShiftTemplate;
import com.example.demo.service.ShiftTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/templates")
public class ShiftTemplateController {

    @Autowired
    private ShiftTemplateService shiftTemplateService;

    @PostMapping("/department/{departmentId}")
    public ResponseEntity<ShiftTemplate> createShiftTemplate(@PathVariable Long departmentId, 
                                                           @RequestBody ShiftTemplate template) {
        template.setDepartmentId(departmentId);
        return ResponseEntity.ok(shiftTemplateService.createShiftTemplate(template));
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<ShiftTemplate>> getTemplatesByDepartment(@PathVariable Long departmentId) {
        return ResponseEntity.ok(shiftTemplateService.getByDepartment(departmentId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShiftTemplate> getTemplate(@PathVariable Long id) {
        // Implementation using service
        return ResponseEntity.ok().build();
    }
}
