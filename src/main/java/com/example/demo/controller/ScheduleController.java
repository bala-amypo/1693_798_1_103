@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/generate")
    public List<GeneratedShiftSchedule> generate(@RequestParam String date) {
        return scheduleService.generateForDate(java.time.LocalDate.parse(date));
    }

    @GetMapping("/date")
    public List<GeneratedShiftSchedule> byDate(@RequestParam String date) {
        return scheduleService.getByDate(java.time.LocalDate.parse(date));
    }
}