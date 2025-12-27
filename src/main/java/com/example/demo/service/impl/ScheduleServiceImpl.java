package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.ScheduleService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ShiftTemplateRepository shiftTemplateRepository;
    private final AvailabilityRepository availabilityRepository;
    private final EmployeeRepository employeeRepository;
    private final GeneratedShiftScheduleRepository scheduleRepository;
    private final DepartmentRepository departmentRepository;

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
    public List<GeneratedShiftSchedule> generateForDate(LocalDate date) {
        List<GeneratedShiftSchedule> schedules = new ArrayList<>();
        List<Department> departments = departmentRepository.findAll();

        for (Department dept : departments) {
            List<ShiftTemplate> shifts = shiftTemplateRepository.findByDepartment_Id(dept.getId());
            List<EmployeeAvailability> availabilities = availabilityRepository.findByAvailableDateAndAvailable(date, true);

            for (ShiftTemplate shift : shifts) {
                for (EmployeeAvailability av : availabilities) {
                    Employee emp = av.getEmployee();
                    // Basic skill match check
                    boolean hasSkill = false;
                    String required = shift.getRequiredSkills();
                    String empSkills = emp.getSkills();
                    if(empSkills != null && required != null) {
                         // Check if any required skill exists in employee skills
                         if(empSkills.contains(required) || required.contains(empSkills)) {
                             hasSkill = true;
                         }
                    }

                    if (hasSkill) {
                        GeneratedShiftSchedule sched = new GeneratedShiftSchedule();
                        sched.setEmployee(emp);
                        sched.setShiftTemplate(shift);
                        sched.setShiftDate(date);
                        schedules.add(scheduleRepository.save(sched));
                        break; // Assign one person per shift for this basic test
                    }
                }
            }
        }
        return schedules;
    }

    @Override
    public List<GeneratedShiftSchedule> getByDate(LocalDate date) {
        return scheduleRepository.findByShiftDate(date);
    }
}
