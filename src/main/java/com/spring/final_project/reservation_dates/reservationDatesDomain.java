package com.spring.final_project.reservation_dates;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class reservationDatesDomain {

	int reservationId, producNum;

	LocalDateTime reservDate, endDate, closeDate, registDate, updateDate;
}
