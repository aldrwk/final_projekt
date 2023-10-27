package com.spring.final_project.payment;

import com.spring.final_project.api.kakao.KakaoApproveResponse;
import com.spring.final_project.member.MemberController;
import com.spring.final_project.product.*;
import com.spring.final_project.reservation.ReservationDomain;
import com.spring.final_project.reservation.ReservationService;
import com.spring.final_project.reservation_dates.ReservationDatesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/pay")
public class paymentController {
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);

	ReservationService reservationService;
	PaymentService paymentService;

	private ProductService productService;
	private ProductOptionService productOptionService;
	private ReservationDatesService reservationDatesService;

	@Autowired
	public paymentController(ProductService productService, ProductOptionService productOptionService, ReservationDatesService reservationDatesService, ReservationService reservationService,
							 PaymentService paymentService) {
		this.productService = productService;
		this.productOptionService = productOptionService;
		this.reservationDatesService = reservationDatesService;
		this.reservationService = reservationService;
		this.paymentService = paymentService;
	}

	@GetMapping("/result")
	public String reservationResult(Model model, HttpSession session, ReservationDomain reservationDomain, PayHistoryDomain payHistoryDomain) {
		KakaoApproveResponse payInfo = (KakaoApproveResponse) session.getAttribute("payInfo");
		String[] idArray = payInfo.getPartner_order_id().split("_");
		int memberNum = Integer.parseInt(idArray[0]);
		int productNum = Integer.parseInt(idArray[1]);
		int optionNum = Integer.parseInt(idArray[2]);
		int quantity = (int) payInfo.getQuantity();
		String productName = payInfo.getItem_name();
		String payMethod = payInfo.getPayment_method_type();
		String payPrice = String.valueOf(payInfo.getAmount().getTotal());
		String payDate = String.valueOf(payInfo.getApproved_at());
		String orderNumber = payInfo.getPartner_order_id();
		Map<String, String> paymentMap = new HashMap<>();
		paymentMap.put("approvedTime", payDate);
		paymentMap.put("productName", productName);
		paymentMap.put("orderNumber", orderNumber);
		paymentMap.put("payPrice", payPrice);
		paymentMap.put("quantity", String.valueOf(quantity));

		reservationDomain = reservationService.setReservation(productNum, memberNum, optionNum, quantity);
		if (1 == reservationService.insert(reservationDomain)) {
			int reservNum = reservationService.getReservNum(memberNum);
			log.info(String.valueOf(reservNum));
			payHistoryDomain = paymentService.setPayHistroy(memberNum, reservNum, payMethod, payPrice, payDate);
			paymentService.insert(payHistoryDomain);
		}
		model.addAttribute("payment", paymentMap);
		return "/payment/result";
	}

	@GetMapping("/cancel")
	public String cancel() {

		return "/payment/cancel";
	}
	@GetMapping("/fail")
	public String fail() {

		return "/payment/fail";
	}

}
