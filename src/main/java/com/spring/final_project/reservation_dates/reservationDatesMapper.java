package com.spring.final_project.reservation_dates;

import com.spring.final_project.util.CalendarVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface reservationDatesMapper {

	public int insert(reservationDatesDomain reservationDates);
	public int update(reservationDatesDomain reservationDates);

	public int delete(reservationDatesDomain reservationDates);

	public List<reservationDatesDomain> findByProduct();

	public List<reservationDatesDomain> findByProductNum(int productNum);

	public List<CalendarVo> findByCalendarVo(int productNum);
	public reservationDatesDomain findById(int reservationId);

	public int getId(int productNum);


}
