package com.spring.final_project.reservation_dates.mapper;

import com.spring.final_project.reservation_dates.ReservationDatesDomain;
import com.spring.final_project.util.CalendarVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReservationDatesWriteMapper {

	public int insert(ReservationDatesDomain reservationDates);
	public int update(ReservationDatesDomain reservationDates);

	public int delete(int productNum);




}
