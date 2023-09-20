package com.spring.final_project.product;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class productController {

	@GetMapping("products/{productNum}")
	public String productList(@PathVariable("productNum") int productNum, Model model) {

		return "product/detail";
	}

	@GetMapping("product/{productNum}/participate")
	public String productCalender(@PathVariable("productNum") int productNum, Model model) {
		return "product/participate";
	}
}
