package com.spring.final_project.reservation_dates;

import com.spring.final_project.util.CalendarVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReservationDatesMapper {

	public int insert(ReservationDatesDomain reservationDates);
	public int update(ReservationDatesDomain reservationDates);

	public int delete(ReservationDatesDomain reservationDates);

	public List<ReservationDatesDomain> findByProduct();

	public List<ReservationDatesDomain> findByProductNum(int productNum);

	public List<CalendarVo> findByCalendarVo(int productNum);
	public ReservationDatesDomain findById(int reservationId);

	public int getId(int productNum);


}
