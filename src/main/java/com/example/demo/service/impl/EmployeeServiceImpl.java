package com.example.demo.service.impl;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repository;
    public EmployeeServiceImpl(EmployeeRepository repo) { this.repository = repo; }

    public Employee createEmployee(Employee e) {
        if (repository.existsByEmail(e.getEmail())) throw new RuntimeException("exists");
        if (e.getExperience() <= 0) throw new RuntimeException("must be > 0");
        return repository.save(e);
    }
    public Employee getEmployee(Long id) { return repository.findById(id).orElseThrow(() -> new RuntimeException("not found")); }
    public Employee updateEmployee(Long id, Employee e) {
        Employee old = getEmployee(id);
        if(!old.getEmail().equals(e.getEmail()) && repository.existsByEmail(e.getEmail())) throw new RuntimeException("exists");
        old.setFullName(e.getFullName());
        return repository.save(old);
    }
    public void deleteEmployee(Long id) { repository.delete(getEmployee(id)); }
    public Employee findByEmail(String email) { return repository.findByEmail(email).orElse(null); }
    public List<Employee> getAll() { return repository.findAll(); }
}