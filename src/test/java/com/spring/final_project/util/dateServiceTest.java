package com.spring.final_project.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

class dateServiceTest {

	@Test
	void reservationCloseDay() {
		LocalDateTime day = LocalDateTime.now();
		System.out.println(day);
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//		String formattedDate = currentDate.format(formatter);
		day= day.minus(1, ChronoUnit.DAYS).withHour(23).withMinute(59);
		System.out.println(day);
//		String dayString = day.toString();
//		String dayRetouch = dayString.substring(0, dayString.length() - 5) + "23:59";
//		day = LocalDateTime.parse(dayRetouch);
	}
}