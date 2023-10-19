package com.spring.final_project.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class dateService {

    public static String toDayTime() {
        LocalDateTime currentDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = currentDate.format(formatter);
        return formattedDate;
    }

    public static String uploadTime() {
        LocalDateTime currentDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String formattedDate = currentDate.format(formatter);
        return formattedDate;
    }
    public static LocalDateTime LocalToDayTime() {
        LocalDateTime currentDate = LocalDateTime.now();
        return currentDate;
    }
    public static String toDay() {
        LocalDateTime currentDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        return formattedDate;
    }

    public static String thisMonth() {
        LocalDateTime thisMonth = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        String formattedMonth = thisMonth.format(formatter);
        return formattedMonth;
    }
}
