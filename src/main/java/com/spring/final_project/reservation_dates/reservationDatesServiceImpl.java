package com.spring.final_project.reservation_dates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class reservationDatesServiceImpl implements reservationDatesService {

	reservationDatesMapper reservationDatesMapper;

	@Autowired
	public reservationDatesServiceImpl(reservationDatesMapper reservationDatesMapper) {
		this.reservationDatesMapper = reservationDatesMapper;
	}

	@Override
	@Transactional
	public int insert(reservationDatesDomain reservationDates) {
		return reservationDatesMapper.insert(reservationDates);
	}

	@Override
	@Transactional
	public int update(reservationDatesDomain reservationDates) {
		return reservationDatesMapper.update(reservationDates);
	}

	@Override
	@Transactional
	public int delete(reservationDatesDomain reservationDates) {
		return reservationDatesMapper.delete(reservationDates);
	}

	@Override
	@Transactional
	public List<reservationDatesDomain> findByProduct() {
		return null;
	}
}
