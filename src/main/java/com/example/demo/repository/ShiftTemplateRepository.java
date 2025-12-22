package com.example.demo.repository;

import com.example.demo.model.ShiftTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShiftTemplateRepository extends JpaRepository<ShiftTemplate, Long> {

    // Basic validations
    boolean existsByTemplateNameAndDepartmentId(String templateName, Long departmentId);
    boolean existsByTemplateNameAndDepartmentIdAndIdNot(String templateName, Long departmentId, Long id);

    // Single lookups (use Optional for safety)
    Optional<ShiftTemplate> findByTemplateNameAndDepartmentId(String templateName, Long departmentId);
    Optional<ShiftTemplate> findByTemplateName(String templateName);

    // Department-specific templates
    List<ShiftTemplate> findByDepartmentIdOrderByTemplateName(Long departmentId);
    Page<ShiftTemplate> findByDepartmentId(Long departmentId, Pageable pageable);

    // Active templates
    List<ShiftTemplate> findByDepartmentIdAndActiveTrueOrderByTemplateName(Long departmentId);
    Page<ShiftTemplate> findByActiveTrue(Pageable pageable);

    // Time-based filtering
    List<ShiftTemplate> findByDepartmentIdAndStartTimeBetween(
            Long departmentId, LocalTime start, LocalTime end);
    
    List<ShiftTemplate> findByStartTimeBeforeAndEndTimeAfter(
            LocalTime time, LocalTime time2);

    // Search functionality
    List<ShiftTemplate> findByTemplateNameContainingIgnoreCaseAndDepartmentIdOrderByStartTime(
            String name, Long departmentId);
    Page<ShiftTemplate> findByTemplateNameContainingIgnoreCaseAndDepartmentId(
            String name, Long departmentId, Pageable pageable);

    // Shift overlap detection
    @Query("SELECT s FROM ShiftTemplate s WHERE s.department.id = :departmentId " +
           "AND ( " +
           "  (s.startTime <= :endTime AND s.endTime >= :startTime) OR " +
           "  (s.startTime <= :endTime2 AND s.endTime >= :startTime2) " +
           ")")
    List<ShiftTemplate> findOverlappingTemplates(
            @Param("departmentId") Long departmentId,
            @Param("startTime") LocalTime startTime,
            @Param("endTime") LocalTime endTime,
            @Param("startTime2") LocalTime startTime2,
            @Param("endTime2") LocalTime endTime2);

    // Statistics queries
    @Query("SELECT s.department.name, COUNT(s), AVG(TIMESTAMPDIFF(MINUTE, s.startTime, s.endTime)) " +
           "FROM ShiftTemplate s GROUP BY s.department.id, s.department.name")
    List<Object[]> getDepartmentTemplateStats();

    @Query("SELECT COUNT(s) FROM ShiftTemplate s WHERE s.department.id = :departmentId")
    long countTemplatesByDepartment(@Param("departmentId") Long departmentId);

    // Bulk operations
    void deleteByDepartmentId(Long departmentId);

    // Native query for reporting
    @Query(value = "SELECT st.*, COUNT(gss.id) as usage_count " +
                   "FROM shift_templates st " +
                   "LEFT JOIN generated_shift_schedules gss ON st.id = gss.shift_template_id " +
                   "WHERE st.department_id = :departmentId " +
                   "GROUP BY st.id " +
                   "ORDER BY usage_count DESC", nativeQuery = true)
    List<Object[]> findTemplatesWithUsageStats(@Param("departmentId") Long departmentId);
}
