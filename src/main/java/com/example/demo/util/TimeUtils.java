package com.example.demo.util;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class TimeUtils {
    public static long minutesBetween(LocalTime start, LocalTime end) {
        return ChronoUnit.MINUTES.between(start, end);
    }
}