package com.spring.final_project.reservation.mapper;

import com.spring.final_project.reservation.ReservationDomain;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReservationWriteMapper {

	public int insert(ReservationDomain reservationDomain);

}
