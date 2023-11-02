package com.spring.final_project.reservation_dates.mapper;

import com.spring.final_project.reservation_dates.ReservationDatesDomain;
import com.spring.final_project.util.CalendarVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReservationDatesReadMapper {

	public List<ReservationDatesDomain> findByProduct();

	public List<ReservationDatesDomain> findByProductNum(int productNum);

	public List<CalendarVo> findByCalendarVo(int productNum);
	public ReservationDatesDomain findById(int reservationId);

	public int getId(int productNum);


}
