@Service
public class ShiftService {

    @Autowired
    private ShiftRepository shiftRepo;

    public void assignShift(Employee employee, Shift shift) throws Exception {
        // Fix for: testEmployeeMaxHoursInvalid - FAIL
        double totalHours = shiftRepo.calculateWeeklyHours(employee.getId());
        double newShiftDuration = Duration.between(shift.getStartTime(), shift.getEndTime()).toHours();

        if (totalHours + newShiftDuration > employee.getMaxHoursPerWeek()) {
            throw new OverWorkException("Employee has exceeded their weekly hour limit.");
        }

        // Fix for: testShiftTemplateUniqueWithinDept - FAIL
        boolean exists = shiftRepo.existsByDepartmentAndName(shift.getDepartment(), shift.getName());
        if (exists) {
            throw new DuplicateShiftException("A shift with this name already exists in the department.");
        }

        shiftRepo.save(shift);
    }
}