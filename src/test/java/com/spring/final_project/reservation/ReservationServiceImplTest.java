package com.spring.final_project.reservation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

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