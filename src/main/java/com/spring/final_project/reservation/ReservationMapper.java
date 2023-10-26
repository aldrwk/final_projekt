package com.spring.final_project.reservation;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReservationMapper {

	public int insert(ReservationDomain reservationDomain);

	public int getReservNum(int memberNum);

}
