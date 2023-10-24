package com.spring.final_project.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ReservationServiceImpl implements ReservationService {

	ReservationMapper reservationMapper;

	@Autowired
	public ReservationServiceImpl(ReservationMapper reservationMapper) {
		this.reservationMapper = reservationMapper;
	}


	@Override
	public  ReservationDomain setReservation(int productNum, int memberNum, int optionNum, int quantity) {

		LocalDateTime now = LocalDateTime.now();

		ReservationDomain reservation = new ReservationDomain();
		reservation.setProducNum(productNum);
		reservation.setMemberNum(memberNum);
		reservation.setOptionId(optionNum);
		reservation.setQuantity(quantity);
		reservation.setReservDate(now);
		return reservation;
	}

	@Override
	@Transactional
	public int insert(ReservationDomain reservationDomain) {
		return reservationMapper.insert(reservationDomain);
	}


	@Override
	@Transactional
	public int getReservNum(int memberNum) {
		return reservationMapper.getReservNum(memberNum);
	}

}
