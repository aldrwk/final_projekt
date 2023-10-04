package com.spring.final_project.member;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.final_project.api.kakao.KakaoService;
import com.spring.final_project.api.kakao.kakaoAcountDto;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Controller
public class memberController {

	private static final Logger logger = LoggerFactory.getLogger(memberController.class);

	private KakaoService kakaoService;
	private memberService memberService;
//	private PasswordEncoder passwordEncoder;

	@Autowired
	public memberController(KakaoService kakaoService, com.spring.final_project.member.memberService memberService) {
		this.kakaoService = kakaoService;
		this.memberService = memberService;
	}

//	@Autowired
//	public memberController(KakaoService kakaoService, memberService memberService, PasswordEncoder passwordEncoder) {
//		this.kakaoService = kakaoService;
//		this.memberService = memberService;
//		this.passwordEncoder = passwordEncoder;
//	}


	@GetMapping("sign-up")
	public String signUp() {
		return "member/signup";
	}

	@PostMapping("signupProc")
	public String signupProc(memberDomain member, Model model) {
		logger.info("test");
		member.setProfile("/image/default_profile.webp");
		LocalDateTime Date = LocalDateTime.now();
		Timestamp currentDate = Timestamp.valueOf(Date);
		member.setChange_date(currentDate);
		member.setCreate_date(currentDate);

//		member.setPassword(passwordEncoder.encode(member.getPassword()));
		int result = memberService.insert(member);
		if (result == 1) {
//			CompletableFuture<Void> future = CompletableFuture.runAsync(() ->{
//				vo.setTo(member.getEmail());
//				vo.setContent(member.getId() + "님 회원 가입");
//				sendMail.sendMail(vo);
//			});
//			redirectAttributes.addFlashAttribute("result", "joinSuccess");
			return "redirect:/";
		}
		return "redirect:/";
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
	@PostMapping("loginProc")
	public String loginProc(String email, String password, Model model, HttpServletRequest request){
		memberDomain member = memberService.findById(email);
		if(!member.password.equals(password)){
			return "/";
		}
		HttpSession session = request.getSession();
		String redirectPath = String.valueOf(session.getAttribute("redirectPath"));
		logger.info(redirectPath);
		System.out.println(redirectPath);
		session.setAttribute("user_info", member);
		return "redirect:" + redirectPath;
	}

	@GetMapping("memberCheck")
	public ResponseEntity<Map<String, Integer>> memberCheck(String email) {
		memberDomain member = memberService.findById(email);
		Map<String, Integer> resultMap = new HashMap<>();
		int result = member == null ? 0 : 1;

		resultMap.put("result", result);

		return ResponseEntity.ok(resultMap);
	}
	@GetMapping("logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		try {
			kakaoService.logoutProc((kakaoAcountDto) session.getAttribute("user_info"));
		} catch (Exception e) {
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
