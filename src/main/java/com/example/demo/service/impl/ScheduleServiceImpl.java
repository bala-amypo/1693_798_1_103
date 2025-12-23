package com.example.demo.service.impl;

import com.example.demo.model.GeneratedShiftSchedule;
import com.example.demo.repository.ScheduleRepository; // Ensure this exists
import com.example.demo.service.ScheduleService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Override
    public List<GeneratedShiftSchedule> generateForDate(LocalDate date) {
        // Logic to generate or fetch schedule for a date
        return scheduleRepository.findByShiftDate(date);
    }

    @Override public GeneratedShiftSchedule saveSchedule(GeneratedShiftSchedule s) { return scheduleRepository.save(s); }
    @Override public List<GeneratedShiftSchedule> getAllSchedules() { return scheduleRepository.findAll(); }
    @Override public void deleteSchedule(Long id) { scheduleRepository.deleteById(id); }
    @Override public List<GeneratedShiftSchedule> getByDate(LocalDate date) { return scheduleRepository.findByShiftDate(date); }
}