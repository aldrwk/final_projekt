package com.spring.final_project.main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class mainController {


	@GetMapping("/")
	public String root(Model model) {
		return "main";
	}
	@PostMapping("/")
	public String root1(Model model) {
		return "main";
	}

	@GetMapping("/calender")
	public String calender() {
		return "calendertest";
	}

	@GetMapping("/test")
	public String test() {
		return "test";
	}

	@PostMapping("/error")
	public String error() {
		return "error/403";
	}
}
