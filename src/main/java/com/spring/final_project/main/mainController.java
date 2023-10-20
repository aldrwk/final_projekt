package com.spring.final_project.main;

import com.spring.final_project.category_first.FirstCategoryDomain;
import com.spring.final_project.category_first.FirstCategoryService;
import com.spring.final_project.product.*;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class mainController {

	private FirstCategoryService firstCategoryService;
	private productService productService;
	private productOptionService productOptionService;

	@Autowired
	public mainController(FirstCategoryService firstCategoryService, productService productService,productOptionService productOptionService) {
		this.firstCategoryService = firstCategoryService;
		this.productService = productService;
		this.productOptionService = productOptionService;
	}

	@GetMapping("/")
	public String root(Model model) {
		List<FirstCategoryDomain> firstCategory = firstCategoryService.findAll();
		List<productDomain> products = productService.findPopular();
		List<Map<String, Object>> productpacks = new ArrayList<>();
		for (productDomain product : products) {
			Map<String, Object> productpack = new HashMap<>();
			int productNum = product.getProducNum();
			productOptionDomain productOption = productOptionService.OneOptionByProduct(productNum);
			productpack.put("product", product);
			productpack.put("productoption", productOption);
			productpacks.add(productpack);
		}

		model.addAttribute("categorys", firstCategory);
		model.addAttribute("productpacks", productpacks);

		return "main";
	}

//	@PostMapping("/")
//	public String root1(Model model) {
//		return "main";
//	}

	@GetMapping("/calendar")
	public String calender() {
		return "calendar";
	}

	@GetMapping("/test")
	public String test(Model model) {

		System.out.println(model.getAttribute("user_info2"));

		return "test";
	}
	@GetMapping("/write")
	public String writeTest() {
		return "/product/write";
	}

	@PostMapping("/error")
	public String error() {
		return "error/403";
	}
}
