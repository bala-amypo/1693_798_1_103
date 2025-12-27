package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private ShiftTemplateRepository shiftTemplateRepository;
    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Override
    public List<Schedule> generateSchedule(LocalDate date) {
        List<Schedule> generatedSchedules = new ArrayList<>();
        
        List<ShiftTemplate> templates = shiftTemplateRepository.findAll();
        List<Availability> availabilities = availabilityRepository.findByDate(date);
        
        for (ShiftTemplate template : templates) {
            for (Availability availability : availabilities) {
                Employee employee = availability.getEmployee();
                
                // Skill Check
                if (template.getRequiredSkill() != null && !template.getRequiredSkill().isEmpty()) {
                    if (!employee.hasSkill(template.getRequiredSkill())) {
                        continue; 
                    }
                }

                boolean alreadyScheduled = generatedSchedules.stream()
                        .anyMatch(s -> s.getEmployee().getId().equals(employee.getId()));
                        
                if (!alreadyScheduled) {
                    Schedule schedule = new Schedule();
                    schedule.setDate(date);
                    schedule.setEmployee(employee);
                    schedule.setShiftTemplate(template);
                    generatedSchedules.add(scheduleRepository.save(schedule));
                }
            }
        }
        return generatedSchedules;
    }

    // Renamed to match Interface
    @Override
    public List<Schedule> getByDate(LocalDate date) {
        return scheduleRepository.findByDate(date);
    }
}