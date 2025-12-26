@Override
public Employee createEmployee(Employee e) {
    if (repository.existsByEmail(e.getEmail())) {
        throw new RuntimeException("exists");
    }
    
    // Ensure this stays to keep testRoleDefaultToStaff passing
    if (e.getRole() == null || e.getRole().isEmpty()) {
        e.setRole("STAFF");
    }
    
    // Ensure this stays for testEmployeeMaxHoursInvalid passing
    if (e.getMaxHoursPerWeek() != null && (e.getMaxHoursPerWeek() < 0 || e.getMaxHoursPerWeek() > 168)) {
        throw new RuntimeException("invalid hours");
    }
    
    return repository.save(e);
}