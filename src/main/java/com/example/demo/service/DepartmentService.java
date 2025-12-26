public interface DepartmentService {
    Department create(Department d); // Test calls this
    Department get(Long id);
    void delete(Long id);
}