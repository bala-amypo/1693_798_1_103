@DeleteMapping("/{id}")
public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
    try {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Deleted"); // Must be exactly "Deleted"
    } catch (RuntimeException e) {
        return ResponseEntity.status(404).body("Employee not found");
    }
}