package com.spring.final_project.reservation_dates;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservationDatesDomain {

	int reservationId, producNum;

	LocalDateTime reservDate, endDate, closeDate, registDate, updateDate;
}
