@PostMapping("/employee/{employeeId}")
public ResponseEntity<EmployeeAvailability> createAvailability(@PathVariable Long employeeId,
                                                             @RequestBody EmployeeAvailability availability) {
    // REMOVE: availability.setEmployeeId(employeeId);
    return ResponseEntity.ok(availabilityService.createEmployeeAvailability(availability));
}
