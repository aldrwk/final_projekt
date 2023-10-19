package com.spring.final_project.main;

import com.spring.final_project.category_first.FirstCategoryDomain;
import com.spring.final_project.category_first.FirstCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class mainController {

	private FirstCategoryService firstCategoryService;

	public mainController(FirstCategoryService firstCategoryService) {
		this.firstCategoryService = firstCategoryService;
	}

	@GetMapping("/")
	public String root(Model model) {
		List<FirstCategoryDomain> firstCategory = firstCategoryService.findAll();
		model.addAttribute("categorys", firstCategory);
		return "main";
	}
	@PostMapping("/")
	public String root1(Model model) {
		return "main";
	}

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
