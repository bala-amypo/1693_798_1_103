package com.example.demo.repository;

import com.example.demo.model.GeneratedShiftSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface GeneratedShiftScheduleRepository extends JpaRepository<GeneratedShiftSchedule, Long> {
    List<GeneratedShiftSchedule> findByShiftDate(LocalDate shiftDate);
}