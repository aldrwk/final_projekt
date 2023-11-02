package com.spring.final_project.reservation.mapper;

import com.spring.final_project.reservation.ReservationDomain;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReservationMapper {

	public int insert(ReservationDomain reservationDomain);

	public int getReservNum(int memberNum);

	public Integer findByReservationDateId(int reservationDateId);
}
