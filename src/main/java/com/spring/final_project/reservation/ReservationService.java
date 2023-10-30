package com.spring.final_project.reservation;

import com.spring.final_project.product.ProductDomain;

import java.util.List;
import java.util.Map;

public interface ReservationService {

	public ReservationDomain setReservation(int productNum, int memberNum, int reservationDateId,String optionName, int quantity);

	public int insert(ReservationDomain reservationDomain);

	public int getReservNum(int memberNum);

	public Integer findByReservationDateId(int reservationDateId);



//	public List<Map<String, Object>> setReservationPack(List<ProductDomain> products);
}
