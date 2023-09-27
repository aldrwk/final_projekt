package com.spring.final_project.member;

import com.spring.final_project.api.kakao.KakaoService;
import com.spring.final_project.api.kakao.kakaoAcountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class memberController {

	private KakaoService kakaoService;

	@Autowired
	public memberController(KakaoService kakaoService) {
		this.kakaoService = kakaoService;
	}

	@GetMapping("sign-up")
	public String signUp() {
		return "member/signup";
	}

	@GetMapping("login")
	public String login(@RequestParam(required = false) String redirectPath, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (redirectPath.equals("/login") || redirectPath.equals("/sign-up")) {
			redirectPath = "/";
		}
		session.setAttribute("redirectPath", redirectPath);
		model.addAttribute("redirectPath", redirectPath);
		return "member/login";
	}

	@GetMapping("logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("user_info") != null) {
			kakaoService.logoutProc((kakaoAcountDto) session.getAttribute("user_info"));
		}
			session.invalidate();

		return "redirect:/";
	}
	@GetMapping("mypage")
	public String mypage(String member, Model model) {

//        Member member로 정보 받아서 ㄱㄱ
		return "member/mypage";
	}

	@GetMapping("/mypage/info")
	public String info(String member, Model model) {
		return "member/info";
	}
}
