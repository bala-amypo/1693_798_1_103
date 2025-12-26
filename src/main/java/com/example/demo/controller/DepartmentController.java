@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    // Test requires this constructor
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        departmentService.delete(id);
    }
}