package com.spring.final_project.reservation_dates;

import com.spring.final_project.util.CalendarVo;

import java.util.List;

public interface ReservationDatesService {



	public int insert(ReservationDatesDomain reservationDates);
	public int update(ReservationDatesDomain reservationDates, int reservationDateId);

	public int delete(int productNum);

	public List<ReservationDatesDomain> findByProductNum(int productNum);

	public List<CalendarVo> findByCalendarVo(int productNum);

	public ReservationDatesDomain findById(int reservationId);

	public int getId(int productNum);
}
