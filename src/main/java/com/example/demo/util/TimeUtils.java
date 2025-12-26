package com.example.demo.util;

import java.time.LocalTime;
import java.time.Duration;

public class TimeUtils {
    public static long minutesBetween(LocalTime start, LocalTime end) {
        if (start == null || end == null) return 0;
        return Duration.between(start, end).toMinutes();
    }
}