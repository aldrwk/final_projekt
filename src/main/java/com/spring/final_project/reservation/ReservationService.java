package com.spring.final_project.reservation;

public interface ReservationService {

	public ReservationDomain setReservation(int productNum, int memberNum, int optionNum, int quantity);

	public int insert(ReservationDomain reservationDomain);

	public int getReservNum(int memberNum);
}
