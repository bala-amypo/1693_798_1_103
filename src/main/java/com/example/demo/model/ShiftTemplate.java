package com.example.demo.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalTime;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class ShiftTemplate {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String templateName;
    private LocalTime startTime;
    private LocalTime endTime;
    private String requiredSkills;
    @ManyToOne @JoinColumn(name = "department_id")
    private Department department;

    public ShiftTemplate(String templateName, LocalTime start, LocalTime end, String skills, Department dept) {
        this.templateName = templateName;
        this.startTime = start;
        this.endTime = end;
        this.requiredSkills = skills;
        this.department = dept;
    }
}