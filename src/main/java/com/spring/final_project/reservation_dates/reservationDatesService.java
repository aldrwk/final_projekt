package com.spring.final_project.reservation_dates;

import com.spring.final_project.util.CalendarVo;

import java.util.List;

public interface reservationDatesService {



	public int insert(reservationDatesDomain reservationDates);
	public int update(reservationDatesDomain reservationDates);

	public int delete(reservationDatesDomain reservationDates);

	public List<reservationDatesDomain> findByProductNum(int productNum);

	public List<CalendarVo> findByCalendarVo(int productNum);

	public reservationDatesDomain findById(int reservationId);

	public int getId(int productNum);
}
