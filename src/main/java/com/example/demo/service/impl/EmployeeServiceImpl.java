// package com.example.demo.service.impl;

// import com.example.demo.model.Employee;
// import com.example.demo.repository.EmployeeRepository;
// import com.example.demo.service.EmployeeService;
// import org.springframework.stereotype.Service;
// import org.springframework.beans.factory.annotation.Autowired;
// import java.util.List;

// @Service
// public class EmployeeServiceImpl implements EmployeeService {

//     private final EmployeeRepository employeeRepository;

//     @Autowired
//     public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
//         this.employeeRepository = employeeRepository;
//     }

//     @Override
//     public Employee createEmployee(Employee employee) {
//         if (employee.getWeeklyHours() <= 0) {
//             throw new IllegalArgumentException("Hours must be greater than 0");
//         }
//         if (employeeRepository.existsByEmail(employee.getEmail())) {
//             throw new RuntimeException("Email exists");
//         }
//         if (employee.getRole() == null) {
//             employee.setRole("STAFF");
//         }
//         return employeeRepository.save(employee);
//     }

//     @Override
//     public Employee getEmployee(Long id) {
//         return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
//     }

//     @Override
//     public Employee updateEmployee(Long id, Employee employee) {
//         Employee existing = getEmployee(id);
//         if (!existing.getEmail().equals(employee.getEmail()) && employeeRepository.existsByEmail(employee.getEmail())) {
//              throw new RuntimeException("Email exists");
//         }
//         existing.setFullName(employee.getFullName());
//         existing.setEmail(employee.getEmail());
//         // Simple update logic for test
//         return employeeRepository.save(existing);
//     }

//     @Override
//     public void deleteEmployee(Long id) {
//         Employee e = getEmployee(id);
//         employeeRepository.delete(e);
//     }

//     @Override
//     public Employee findByEmail(String email) {
//         return employeeRepository.findByEmail(email).orElse(null);
//     }

//     @Override
//     public List<Employee> getAll() {
//         return employeeRepository.findAll();
//     }
// }
package com.example.demo.service.impl;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        if (employeeRepository.findByEmail(employee.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployee(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    // This was the missing method causing the compilation error
    @Override
    public Employee findByEmail(String email) {
        return employeeRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException("Employee not found");
        }
        employee.setId(id);
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException("Employee not found");
        }
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }
}