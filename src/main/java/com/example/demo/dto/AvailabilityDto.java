package com.example.demo.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class AvailabilityDto {
    private LocalDate availableDate;
    private Boolean available;
}