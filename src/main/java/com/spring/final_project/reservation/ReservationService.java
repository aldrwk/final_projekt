package com.spring.final_project.reservation;

import com.spring.final_project.product.ProductDomain;

import java.util.List;
import java.util.Map;

public interface ReservationService {

	public ReservationDomain setReservation(int productNum, int memberNum, int optionNum, int quantity);

	public int insert(ReservationDomain reservationDomain);

	public int getReservNum(int memberNum);

//	public List<Map<String, Object>> setReservationPack(List<ProductDomain> products);
}
