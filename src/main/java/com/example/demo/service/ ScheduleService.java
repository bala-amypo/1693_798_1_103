package com.example.demo.service;

import com.example.demo.model.GeneratedShiftSchedule;
import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {

    /**
     * Automatically generates a schedule for a specific department and date range.
     */
    List<GeneratedShiftSchedule> generateAutoSchedule(Long departmentId, LocalDate startDate, LocalDate endDate);

    /**
     * Retrieves all generated schedules for a specific date.
     */
    List<GeneratedShiftSchedule> getSchedulesByDate(LocalDate date);

    /**
     * Retrieves the schedule for a specific employee.
     */
    List<GeneratedShiftSchedule> getSchedulesByEmployee(Long employeeId);

    /**
     * Deletes a specific schedule entry.
     */
    void deleteSchedule(Long id);
}