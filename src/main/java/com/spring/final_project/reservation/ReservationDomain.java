package com.spring.final_project.reservation;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservationDomain {

	int reservNum, producNum, memberNum, reservationId, optionId, quantity;
	LocalDateTime reservDate;
}
