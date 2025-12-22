package com.example.demo.repository;

import com.example.demo.model.GeneratedShiftSchedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface GeneratedShiftScheduleRepository extends JpaRepository<GeneratedShiftSchedule, Long> {

    // Basic date queries
    List<GeneratedShiftSchedule> findByShiftDate(LocalDate date);
    List<GeneratedShiftSchedule> findByShiftDateOrderByStartTime(LocalDate date);

    // Department-specific shifts
    List<GeneratedShiftSchedule> findByDepartmentIdAndShiftDate(Long departmentId, LocalDate date);
    Page<GeneratedShiftSchedule> findByDepartmentIdAndShiftDate(Long departmentId, LocalDate date, Pageable pageable);

    // Employee-specific shifts
    List<GeneratedShiftSchedule> findByEmployeeIdAndShiftDate(Long employeeId, LocalDate date);
    Optional<GeneratedShiftSchedule> findByEmployeeIdAndShiftDateAndShiftTemplateId(
            Long employeeId, LocalDate date, Long shiftTemplateId);

    // Date range queries
    List<GeneratedShiftSchedule> findByShiftDateBetweenOrderByShiftDateAscStartTimeAsc(
            LocalDate startDate, LocalDate endDate);
    List<GeneratedShiftSchedule> findByDepartmentIdAndShiftDateBetween(
            Long departmentId, LocalDate startDate, LocalDate endDate);

    // Conflict detection (overlapping shifts)
    @Query("SELECT s FROM GeneratedShiftSchedule s WHERE s.department.id = :departmentId " +
           "AND s.shiftDate = :date " +
           "AND ( " +
           "  (s.startTime <= :endTime AND s.endTime >= :startTime) OR " +
           "  (s.startTime <= :endTime2 AND s.endTime >= :startTime2) " +
           ")")
    List<GeneratedShiftSchedule> findOverlappingShifts(
            @Param("departmentId") Long departmentId,
            @Param("date") LocalDate date,
            @Param("startTime") LocalTime startTime,
            @Param("endTime") LocalTime endTime,
            @Param("startTime2") LocalTime startTime2,
            @Param("endTime2") LocalTime endTime2);

    // Employee availability for shifts
    @Query("SELECT s FROM GeneratedShiftSchedule s WHERE s.department.id = :departmentId " +
           "AND s.shiftDate = :date " +
           "AND s.employee IS NULL")
    List<GeneratedShiftSchedule> findUnassignedShiftsByDepartmentAndDate(
            @Param("departmentId") Long departmentId, @Param("date") LocalDate date);

    // Shift statistics
    @Query("SELECT s.department.name, COUNT(s), AVG(TIMESTAMPDIFF(MINUTE, s.startTime, s.endTime)) " +
           "FROM GeneratedShiftSchedule s " +
           "WHERE s.shiftDate BETWEEN :startDate AND :endDate " +
           "GROUP BY s.department.id, s.department.name")
    List<Object[]> getDepartmentShiftStats(@Param("startDate") LocalDate startDate, 
                                          @Param("endDate") LocalDate endDate);

    // Bulk operations
    void deleteByDepartmentIdAndShiftDate(Long departmentId, LocalDate date);
    void deleteByEmployeeIdAndShiftDateBetween(Long employeeId, LocalDate startDate, LocalDate endDate);

    // Reporting queries
    @Query(value = "SELECT DATE(s.shift_date), COUNT(*) " +
                   "FROM generated_shift_schedules s " +
                   "WHERE s.department_id = :departmentId " +
                   "GROUP BY DATE(s.shift_date) " +
                   "ORDER BY DATE(s.shift_date) DESC", nativeQuery = true)
    List<Object[]> getDailyShiftCountByDepartment(@Param("departmentId") Long departmentId);

    // Find shifts by time window
    List<GeneratedShiftSchedule> findByShiftDateAndStartTimeBetween(
            LocalDate date, LocalTime start, LocalTime end);
}
