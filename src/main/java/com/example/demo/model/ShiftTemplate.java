public void setDepartmentId(Long departmentId) {
    if (department == null) {
        department = new Department();
    }
    department.setId(departmentId);
}
