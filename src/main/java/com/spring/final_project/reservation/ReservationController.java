package com.spring.final_project.reservation;

import com.spring.final_project.api.kakao.KakaoApproveResponse;
import com.spring.final_project.member.MemberController;
import com.spring.final_project.payment.PayHistoryDomain;
import com.spring.final_project.payment.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/reservation")
public class ReservationController {
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);

	ReservationService reservationService;
	PaymentService payHistoryService;

	@Autowired
	public ReservationController(ReservationService reservationService, PaymentService payHistoryService) {
		this.reservationService = reservationService;
		this.payHistoryService = payHistoryService;
	}

	@GetMapping("/result")
	public String reservationResult(HttpSession session, ReservationDomain reservationDomain, PayHistoryDomain payHistoryDomain) {
		KakaoApproveResponse payInfo = (KakaoApproveResponse) session.getAttribute("payInfo");
		String[] idArray = payInfo.getPartner_order_id().split("_");
		int memberNum = Integer.parseInt(idArray[0]);
		int productNum = Integer.parseInt(idArray[1]);
		int optionNum = Integer.parseInt(idArray[2]);
		int quantity = (int) payInfo.getQuantity();

		String payMethod = payInfo.getPayment_method_type();
		String payPrice = String.valueOf(payInfo.getAmount().getTotal());
		String payDate = String.valueOf(payInfo.getApproved_at());
		reservationDomain = reservationService.setReservation(productNum, memberNum, optionNum, quantity);
		if (1 == reservationService.insert(reservationDomain)) {
			int reservNum = reservationService.getReservNum(memberNum);
			log.info(String.valueOf(reservNum));
			payHistoryDomain = payHistoryService.setPayHistroy(memberNum, reservNum, payMethod, payPrice, payDate);
			payHistoryService.insert(payHistoryDomain);
		}
		return "/test";
	}


}
