package com.example.demo.controller;

import com.example.demo.model.GeneratedShiftSchedule;
import com.example.demo.service.ScheduleService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/generate")
    public List<GeneratedShiftSchedule> generate(@RequestParam String date) {
        return scheduleService.generateForDate(LocalDate.parse(date));
    }

    @GetMapping("/date")
    public List<GeneratedShiftSchedule> byDate(@RequestParam String date) {
        return scheduleService.getByDate(LocalDate.parse(date));
    }
}