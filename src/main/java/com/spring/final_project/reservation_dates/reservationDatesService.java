package com.spring.final_project.reservation_dates;

import java.util.List;

public interface reservationDatesService {



	public int insert(reservationDatesDomain reservationDates);
	public int update(reservationDatesDomain reservationDates);

	public int delete(reservationDatesDomain reservationDates);

	public List<reservationDatesDomain> findByProduct();
}
