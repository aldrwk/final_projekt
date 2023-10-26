package com.spring.final_project.reservation;

import com.spring.final_project.product.ProductDomain;
import com.spring.final_project.product.ProductOptionDomain;
import com.spring.final_project.reservation_dates.reservationDatesDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ReservationServiceImpl implements ReservationService {

	ReservationMapper reservationMapper;

	@Autowired
	public ReservationServiceImpl(ReservationMapper reservationMapper) {
		this.reservationMapper = reservationMapper;
	}


	@Override
	public  ReservationDomain setReservation(int productNum, int memberNum, int optionNum, int quantity) {

		LocalDateTime now = LocalDateTime.now();

		ReservationDomain reservation = new ReservationDomain();
		reservation.setProducNum(productNum);
		reservation.setMemberNum(memberNum);
		reservation.setOptionId(optionNum);
		reservation.setQuantity(quantity);
		reservation.setReservDate(now);
		return reservation;
	}

	@Override
	@Transactional
	public int insert(ReservationDomain reservationDomain) {
		return reservationMapper.insert(reservationDomain);
	}


	@Override
	@Transactional
	public int getReservNum(int memberNum) {
		return reservationMapper.getReservNum(memberNum);
	}

//	@Override
//	public List<Map<String, Object>> setReservationPack(List<reservationDatesDomain> reservationDates) {
//		List<Map<String, Object>> productpacks = new ArrayList<>();
//		for (ProductDomain product : products) {
//			Map<String, Object> productpack = new HashMap<>();
//			int productNum = product.getProducNum();
//			ProductOptionDomain productOption = productOptionService.OneOptionByProduct(productNum);
//			Locale locale = new Locale("ko", "KR"); // 한국 로케일 (한국어, 대한민국)
//			NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);
//			String newPrice = String.valueOf(numberFormat.format(Integer.parseInt(productOption.getPrice())));
//			productOption.setPrice(newPrice);
//			productpack.put("product", product);
//			productpack.put("productoption", productOption);
//			productpacks.add(productpack);
//		}
//		return productpacks;
//	}


}
