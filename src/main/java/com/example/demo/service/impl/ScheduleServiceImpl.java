package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ShiftTemplateRepository shiftTemplateRepository;

    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private GeneratedShiftScheduleRepository scheduleRepository;

    @Override
    public List<GeneratedShiftSchedule> generateForDate(LocalDate date) {
        List<ShiftTemplate> templates = shiftTemplateRepository.findAll();
        List<EmployeeAvailability> availabilities = availabilityRepository.findByAvailableDateAndAvailable(date, true);
        
        List<GeneratedShiftSchedule> schedules = new ArrayList<>();
        
        for (ShiftTemplate template : templates) {
            Employee assignedEmployee = findBestEmployee(template, availabilities);
            if (assignedEmployee != null) {
                GeneratedShiftSchedule schedule = new GeneratedShiftSchedule();
                schedule.setShiftDate(date);
                schedule.setStartTime(template.getStartTime());
                schedule.setEndTime(template.getEndTime());
                schedule.setShiftTemplate(template);
                schedule.setDepartment(template.getDepartment());
                schedule.setEmployee(assignedEmployee);
                schedules.add(scheduleRepository.save(schedule));
            }
        }
        
        return schedules;
    }

    private Employee findBestEmployee(ShiftTemplate template, List<EmployeeAvailability> availabilities) {
        for (EmployeeAvailability availability : availabilities) {
            Employee employee = availability.getEmployee();
            if (matchesSkills(employee.getSkills(), template.getRequiredSkills())) {
                return employee;
            }
        }
        return null;
    }

    private boolean matchesSkills(String employeeSkills, String requiredSkills) {
        if (employeeSkills == null || requiredSkills == null) return false;
        String[] employeeSkillList = employeeSkills.split(",");
        String[] requiredSkillList = requiredSkills.split(",");
        for (String required : requiredSkillList) {
            boolean hasSkill = false;
            for (String employeeSkill : employeeSkillList) {
                if (employeeSkill.trim().equalsIgnoreCase(required.trim())) {
                    hasSkill = true;
                    break;
                }
            }
            if (!hasSkill) return false;
        }
        return true;
    }

    @Override
    public List<GeneratedShiftSchedule> getByDate(LocalDate date) {
        return scheduleRepository.findByShiftDate(date);
    }
}
