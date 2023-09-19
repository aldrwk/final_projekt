package com.spring.final_project.payment;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("pay")
public class paymentController {

	@GetMapping("{productNum}/pay-method")
	public String payment(@PathVariable("productNum") int productNum, Model model,
						  String totalPrice, String date, String time, String productName) {
		model.addAttribute("productNum", productNum);
		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("date", date);
		model.addAttribute("time", time);
		model.addAttribute("productName", productName);
		return "/payment/pay-method";
	}

}
