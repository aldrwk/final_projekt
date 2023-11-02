package com.spring.final_project.reservation;

import com.spring.final_project.reservation.mapper.ReservationMapper;
import org.junit.jupiter.api.Test;

class ReservationServiceImplTest {

	ReservationMapper reservationMapper;

	public ReservationServiceImplTest(ReservationMapper reservationMapper) {
		this.reservationMapper = reservationMapper;
	}

	@Test
	void getReservNum() {
		int test = reservationMapper.getReservNum(25);
		System.out.println(test);
	}
}