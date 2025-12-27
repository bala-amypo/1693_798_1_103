package com.example.demo.controller;

import com.example.demo.model.GeneratedShiftSchedule;
import com.example.demo.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/generate/{date}")
    public ResponseEntity<List<GeneratedShiftSchedule>> generate(@PathVariable String date) {
        return ResponseEntity.ok(scheduleService.generateForDate(LocalDate.parse(date)));
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<GeneratedShiftSchedule>> byDate(@PathVariable String date) {
        return ResponseEntity.ok(scheduleService.getByDate(LocalDate.parse(date)));
    }
}