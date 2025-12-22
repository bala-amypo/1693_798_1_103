@PostMapping("/department/{departmentId}")
public ResponseEntity<ShiftTemplate> createShiftTemplate(@PathVariable Long departmentId, 
                                                       @RequestBody ShiftTemplate template) {
    // REMOVE: template.setDepartmentId(departmentId);
    return ResponseEntity.ok(shiftTemplateService.createShiftTemplate(template));
}
