package com.spring.final_project.util;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

@Data
public class CalendarVo {

	int reservationId, maxPeople, maxPerson;
	LocalDateTime reservDate, endDate;

}
