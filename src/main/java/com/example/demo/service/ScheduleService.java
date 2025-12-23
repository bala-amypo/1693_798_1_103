package com.example.demo.service;

import com.example.demo.model.GeneratedShiftSchedule;
import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {
    // This exact method signature must exist here
    List<GeneratedShiftSchedule> generateForDate(LocalDate date);
}