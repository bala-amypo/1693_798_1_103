package com.example.demo.service.impl;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.ScheduleService;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final ShiftTemplateRepository shiftRepo;
    private final AvailabilityRepository availRepo;
    private final EmployeeRepository empRepo;
    private final GeneratedShiftScheduleRepository schedRepo;
    private final DepartmentRepository deptRepo;

    public ScheduleServiceImpl(ShiftTemplateRepository st, AvailabilityRepository av, 
                               EmployeeRepository em, GeneratedShiftScheduleRepository sc, DepartmentRepository de) {
        this.shiftRepo = st; this.availRepo = av; this.empRepo = em; this.schedRepo = sc; this.deptRepo = de;
    }

    public List<GeneratedShiftSchedule> generateForDate(LocalDate date) {
        List<GeneratedShiftSchedule> results = new ArrayList<>();
        List<EmployeeAvailability> availableItems = availRepo.findByAvailableDateAndAvailable(date, true);
        List<Department> depts = deptRepo.findAll();

        for (Department d : depts) {
            List<ShiftTemplate> templates = shiftRepo.findByDepartment_Id(d.getId());
            for (ShiftTemplate st : templates) {
                for (EmployeeAvailability av : availableItems) {
                    if (av.getEmployee().getSkills().contains(st.getRequiredSkills())) {
                        GeneratedShiftSchedule gs = new GeneratedShiftSchedule();
                        gs.setEmployee(av.getEmployee());
                        gs.setShiftDate(date);
                        results.add(schedRepo.save(gs));
                    }
                }
            }
        }
        return results;
    }

    public List<GeneratedShiftSchedule> getByDate(LocalDate date) { return schedRepo.findByShiftDate(date); }
}