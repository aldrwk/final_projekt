package com.spring.final_project.member;

import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
public class memberController {

	@GetMapping("sign-up")
	public String signUp() {
		return "member/signup";
	}

	@GetMapping("login")
	public String login(@RequestParam String redirectPath, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("redirectPath",redirectPath);

		model.addAttribute("redirectPath", redirectPath);
		return "member/login";
	}

	@GetMapping("mypage")
	public String mypage(String member, Model model) {
//        Member member로 정보 받아서 ㄱㄱ
		return "member/mypage";
	}





}
