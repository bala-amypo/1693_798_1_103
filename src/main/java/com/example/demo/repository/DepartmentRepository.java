package com.example.demo.repository;

import com.example.demo.model.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    // Basic validations
    boolean existsByNameIgnoreCase(String name);
    boolean existsByNameAndIdNot(String name, Long id);

    // Single lookups
    Optional<Department> findByNameIgnoreCase(String name);
    Optional<Department> findByName(String name);

    // List operations
    List<Department> findByNameContainingIgnoreCaseOrderByName(String name);
    List<Department> findAllByOrderByName();

    // Pagination
    Page<Department> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Department> findAll(Pageable pageable);

    // Custom queries
    @Query("SELECT d FROM Department d WHERE LOWER(d.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Department> searchByKeyword(@Param("keyword") String keyword);

    @Query("SELECT COUNT(d) FROM Department d WHERE d.requiredSkills LIKE %:skill%")
    long countDepartmentsWithSkill(@Param("skill") String skill);

    // Employee count per department
    @Query("SELECT d.name, COUNT(e) FROM Department d LEFT JOIN d.employees e GROUP BY d.id, d.name ORDER BY COUNT(e) DESC")
    List<Object[]> findDepartmentNamesWithEmployeeCount();

    // Active departments (if you add active flag)
    List<Department> findByActiveTrueOrderByName();

    // Bulk operations
    void deleteByName(String name);

    // Native query for complex reporting
    @Query(value = "SELECT d.*, COUNT(s.id) as shift_count " +
                   "FROM departments d " +
                   "LEFT JOIN generated_shift_schedules s ON d.id = s.department_id " +
                   "GROUP BY d.id " +
                   "ORDER BY shift_count DESC", nativeQuery = true)
    List<Object[]> findDepartmentsWithShiftCounts();
}
