package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shift_templates")
public class ShiftTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String templateName;
    private LocalTime startTime;
    private LocalTime endTime;
    private String requiredSkill; // Can be null

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    // --- FIX: Validation Logic for Time ---
    public void setStartTime(LocalTime startTime) {
        validateTime(startTime, this.endTime);
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        validateTime(this.startTime, endTime);
        this.endTime = endTime;
    }

    private void validateTime(LocalTime start, LocalTime end) {
        if (start != null && end != null && !end.isAfter(start)) {
            throw new IllegalArgumentException("End time must be after start time");
        }
    }
}