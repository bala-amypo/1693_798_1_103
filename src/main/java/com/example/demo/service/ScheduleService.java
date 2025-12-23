package com.example.demo.service;

import com.example.demo.model.GeneratedShiftSchedule;
import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {
    GeneratedShiftSchedule saveSchedule(GeneratedShiftSchedule schedule);
    List<GeneratedShiftSchedule> getAllSchedules();
    void deleteSchedule(Long id);
    List<GeneratedShiftSchedule> getByDate(LocalDate date);
}