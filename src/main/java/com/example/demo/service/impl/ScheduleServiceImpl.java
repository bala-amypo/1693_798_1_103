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

    private ShiftTemplateRepository shiftTemplateRepository;
    private AvailabilityRepository availabilityRepository;
    private EmployeeRepository employeeRepository;
    private GeneratedShiftScheduleRepository scheduleRepository;
    private DepartmentRepository departmentRepository;

    // MANDATORY: The test file uses this exact constructor
    @Autowired
    public ScheduleServiceImpl(ShiftTemplateRepository shiftTemplateRepository,
                               AvailabilityRepository availabilityRepository,
                               EmployeeRepository employeeRepository,
                               GeneratedShiftScheduleRepository scheduleRepository,
                               DepartmentRepository departmentRepository) {
        this.shiftTemplateRepository = shiftTemplateRepository;
        this.availabilityRepository = availabilityRepository;
        this.employeeRepository = employeeRepository;
        this.scheduleRepository = scheduleRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    // MANDATORY: Test calls "generateForDate", not "generateSchedule"
    public List<GeneratedShiftSchedule> generateForDate(LocalDate date) {
        List<GeneratedShiftSchedule> schedules = new ArrayList<>();

        // 1. Get all departments
        List<Department> departments = departmentRepository.findAll();

        for (Department dept : departments) {
            // 2. Get shifts for dept
            List<ShiftTemplate> shifts = shiftTemplateRepository.findByDepartment_Id(dept.getId());

            // 3. Get available employees for this date
            List<EmployeeAvailability> availabilities = availabilityRepository
                    .findByAvailableDateAndAvailable(date, true);

            for (ShiftTemplate shift : shifts) {
                for (EmployeeAvailability av : availabilities) {
                    Employee emp = av.getEmployee();
                    
                    // Simple logic: If employee has the required skill, schedule them
                    if (emp.getSkills().contains(shift.getRequiredSkills())) {
                         GeneratedShiftSchedule sched = new GeneratedShiftSchedule();
                         sched.setEmployee(emp);
                         sched.setShiftTemplate(shift);
                         sched.setShiftDate(date);
                         
                         schedules.add(scheduleRepository.save(sched));
                         break; // One person per shift for simplicity in this logic
                    }
                }
            }
        }
        return schedules;
    }

    @Override
    // Match test: scheduleService.getByDate(d)
    public List<GeneratedShiftSchedule> getByDate(LocalDate date) {
        return scheduleRepository.findByShiftDate(date);
    }
}