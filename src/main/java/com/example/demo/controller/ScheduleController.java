package com.example.demo.controller;

import com.example.demo.model.GeneratedShiftSchedule;
import com.example.demo.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/generate")
    public ResponseEntity<List<GeneratedShiftSchedule>> generate(@RequestParam String date) {
        return ResponseEntity.ok(scheduleService.generateForDate(LocalDate.parse(date)));
    }

    @GetMapping("/date")
    public ResponseEntity<List<GeneratedShiftSchedule>> byDate(@RequestParam String date) {
        return ResponseEntity.ok(scheduleService.getByDate(LocalDate.parse(date)));
    }
}