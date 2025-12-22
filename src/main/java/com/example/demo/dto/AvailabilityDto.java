package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class AvailabilityDto {
    @NotNull(message = "Available date is required")
    private LocalDate availableDate;

    @NotNull(message = "Availability status is required")
    private Boolean available;

    // Default constructor
    public AvailabilityDto() {}

    public AvailabilityDto(LocalDate availableDate, Boolean available) {
        this.availableDate = availableDate;
        this.available = available;
    }

    public LocalDate getAvailableDate() {
        return availableDate;
    }

    public void setAvailableDate(LocalDate availableDate) {
        this.availableDate = availableDate;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
