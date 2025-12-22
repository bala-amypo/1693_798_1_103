package com.example.demo.repository;

import com.example.demo.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Basic validations
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, Long id);
    boolean existsByEmployeeCode(String employeeCode);

    // Single lookups
    Optional<Employee> findByEmail(String email);
    Optional<Employee> findByEmployeeCode(String employeeCode);
    Optional<Employee> findByEmailIgnoreCase(String email);

    // Department-based queries
    List<Employee> findByDepartmentIdOrderByName(Long departmentId);
    Page<Employee> findByDepartmentId(Long departmentId, Pageable pageable);

    // Active employees
    List<Employee> findByActiveTrueOrderByName();
    Page<Employee> findByActiveTrue(Pageable pageable);

    // Search functionality
    List<Employee> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrderByName(
            String name, String email);
    Page<Employee> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
            String name, String email, Pageable pageable);

    // Availability & Shift queries
    @Query("SELECT e FROM Employee e JOIN e.availabilities a " +
           "WHERE a.availableDate = :date AND a.available = true " +
           "AND e.department.id = :departmentId")
    List<Employee> findAvailableEmployeesByDepartmentOnDate(
            @Param("departmentId") Long departmentId,
            @Param("date") LocalDate date);

    // Skill-based search
    @Query("SELECT e FROM Employee e WHERE " +
           "LOWER(e.skills) LIKE LOWER(CONCAT('%', :skill, '%')) " +
           "OR e.department.requiredSkills LIKE LOWER(CONCAT('%', :skill, '%'))")
    List<Employee> findBySkillsOrDepartmentSkills(@Param("skill") String skill);

    // HR Reports
    @Query("SELECT e.department.name, COUNT(e) FROM Employee e " +
           "GROUP BY e.department.id, e.department.name " +
           "ORDER BY COUNT(e) DESC")
    List<Object[]> countEmployeesByDepartment();

    @Query("SELECT AVG(DATEDIFF(CURDATE(), e.joinDate)) FROM Employee e WHERE e.active = true")
    Double getAverageEmployeeTenureDays();

    // Bulk operations
    void deleteByDepartmentId(Long departmentId);
    List<Employee> findByJoinDateBetween(LocalDate startDate, LocalDate endDate);

    // Native query for complex reporting
    @Query(value = "SELECT e.*, COUNT(s.id) as shift_count " +
                   "FROM employees e " +
                   "LEFT JOIN generated_shift_schedules s ON e.id = s.employee_id " +
                   "WHERE e.active = 1 " +
                   "GROUP BY e.id " +
                   "ORDER BY shift_count DESC", nativeQuery = true)
    List<Object[]> findEmployeesWithShiftCounts();
}
