package com.spring.final_project.api.naver;

import com.spring.final_project.member.memberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("api/naver")
public class naverSmsController {

	private memberService memberService;
	private naverSmsService naverSmsService;

	@Autowired
	public naverSmsController(memberService memberService, naverSmsService naverSmsService) {
		this.memberService = memberService;
		this.naverSmsService = naverSmsService;
	}

	@PostMapping("/mobileAuth")
	@ResponseBody
	public Boolean mobileAuth(@RequestParam String mobile, HttpServletRequest request) {
		HttpSession session = request.getSession();
		System.out.println(mobile);

		// 이미 가입된 전화번호가 있으면
		if (memberService.findByMobile(mobile) != null) {
			return true;

		}
		String code = naverSmsService.sendRandomMessage(mobile);
		session.setAttribute("rand", code);

		return false;
	}

	@PostMapping("/mobileAuthOk")
	@ResponseBody
	public Boolean mobileAuthOk(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String rand = String.valueOf(session.getAttribute("rand"));
		String code = request.getParameter("AuthNum");

		System.out.println(rand + " : " + code);

		if (rand.equals(code)) {
			session.removeAttribute("rand");
			return true;
		}
		return false;
	}
}
