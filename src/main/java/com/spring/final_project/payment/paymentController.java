package com.spring.final_project.payment;

import com.spring.final_project.product.*;
import com.spring.final_project.reservation_dates.reservationDatesDomain;
import com.spring.final_project.reservation_dates.reservationDatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pay")
public class paymentController {

	private productService productService;
	private productOptionService productOptionService;
	private reservationDatesService reservationDatesService;


	@Autowired
	public paymentController(productService productService, productOptionService productOptionService, reservationDatesService reservationDatesService) {
		this.productService = productService;
		this.productOptionService = productOptionService;
		this.reservationDatesService = reservationDatesService;
	}

	@PostMapping("/pay-method")
	public String payment(Model model, String totalPrice, int reservId, int optionId, int productNum) {
		productDomain product = productService.findByProductNum(productNum);
		productOptionDomain productOptions = productOptionService.optionsById(optionId);
		reservationDatesDomain reservationDate = reservationDatesService.findById(reservId);

		model.addAttribute("productNum", productNum);
		model.addAttribute("product", product);
		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("productOptions", productOptions);
		model.addAttribute("reservationDate", reservationDate);

		return "/payment/pay-method";
	}

}
