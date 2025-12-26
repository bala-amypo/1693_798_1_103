@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    private final DepartmentService departmentService;
    public DepartmentController(DepartmentService ds) { this.departmentService = ds; }

    @GetMapping("/{id}")
    public ResponseEntity<Department> get(@PathVariable Long id) {
        Department d = departmentService.get(id);
        // FIX: Return 404 if null for test assertions
        return d != null ? ResponseEntity.ok(d) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        // FIX: Logic for testDeleteMissingDepartment
        if (departmentService.get(id) == null) {
            return ResponseEntity.notFound().build();
        }
        departmentService.delete(id);
        return ResponseEntity.ok().build();
    }
}