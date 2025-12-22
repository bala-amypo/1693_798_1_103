package com.example.demo.service.impl;

import com.example.demo.model.GeneratedShiftSchedule;
import com.example.demo.repository.ScheduleRepository;
import com.example.demo.service.ScheduleService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Override
    public GeneratedShiftSchedule saveSchedule(GeneratedShiftSchedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public List<GeneratedShiftSchedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }
}