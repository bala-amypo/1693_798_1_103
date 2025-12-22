public void setEmployeeId(Long employeeId) {
    if (employee == null) {
        employee = new Employee();
    }
    employee.setId(employeeId);
}
