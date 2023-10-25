package com.spring.final_project.payment;

import com.spring.final_project.product.*;
import com.spring.final_project.reservation_dates.reservationDatesDomain;
import com.spring.final_project.reservation_dates.reservationDatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/pay")
public class paymentController {

	private ProductService productService;
	private ProductOptionService productOptionService;
	private reservationDatesService reservationDatesService;

	@Autowired
	public paymentController(ProductService productService, ProductOptionService productOptionService, reservationDatesService reservationDatesService) {
		this.productService = productService;
		this.productOptionService = productOptionService;
		this.reservationDatesService = reservationDatesService;
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
