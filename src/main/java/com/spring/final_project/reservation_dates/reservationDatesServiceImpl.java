package com.spring.final_project.reservation_dates;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class reservationDatesServiceImpl implements reservationDatesService {

	reservationDatesMapper reservationDatesMapper;

	public reservationDatesServiceImpl(reservationDatesMapper reservationDatesMapper) {
		this.reservationDatesMapper = reservationDatesMapper;
	}

	@Override
	public int insert(reservationDatesDomain reservationDates) {
		return reservationDatesMapper.insert(reservationDates);
	}

	@Override
	public int update(reservationDatesDomain reservationDates) {
		return reservationDatesMapper.update(reservationDates);
	}

	@Override
	public int delete(reservationDatesDomain reservationDates) {
		return reservationDatesMapper.delete(reservationDates);
	}

	@Override
	public List<reservationDatesDomain> findByProduct() {
		return null;
	}
}
