package com.example.demo.service;

import com.example.demo.model.Schedule;
import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {
    List<Schedule> generateSchedule(LocalDate date);
    List<Schedule> getByDate(LocalDate date);
}