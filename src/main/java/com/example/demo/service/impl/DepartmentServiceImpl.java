@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    public DepartmentServiceImpl(DepartmentRepository repo) { this.departmentRepository = repo; }

    @Override
    public Department create(Department d) {
        // FIX: Check for duplicate department names
        if (departmentRepository.findByName(d.getName()).isPresent()) {
            throw new RuntimeException("Department name already exists");
        }
        return departmentRepository.save(d);
    }

    @Override
    public Department get(Long id) { return departmentRepository.findById(id).orElse(null); }

    @Override
    public List<Department> getAll() { return departmentRepository.findAll(); }

    @Override
    public void delete(Long id) { departmentRepository.deleteById(id); }
}