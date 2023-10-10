package com.spring.final_project.api.kakao;

import com.spring.final_project.member.memberDomain;
import com.spring.final_project.member.memberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/api/login")
public class kakaoController {
	private static final Logger log = LoggerFactory.getLogger(kakaoController.class);
	private final KakaoService kakaoService;
	private kakaoAcountDto kakaoAcountDto;

	private memberService memberService;

	@Autowired
	public kakaoController(KakaoService kakaoService, kakaoAcountDto kakaoAcountDto, memberService memberService) {
		this.kakaoService = kakaoService;
		this.kakaoAcountDto = kakaoAcountDto;
		this.memberService = memberService;
	}

	@GetMapping("/kakaoauth")
	public String kakaoLoginURL(HttpServletRequest request) {
		String redirectPath = request.getParameter("redirectPath");
		String REST_API_KEY = "cfba8202c491dd4272811ab220211412";
		String REDIRECT_URI = "http://localhost/api/login/kakao";

		String kakaoLoginURL = String.format("redirect:https://kauth.kakao.com/oauth/authorize?client_id=%s&redirect_uri=%s&response_type=code", REST_API_KEY, REDIRECT_URI);
		return kakaoLoginURL;

	}

	@GetMapping("/kakao")
	public String kakaoCallBack(@RequestParam String code, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String redirectPath = (String) session.getAttribute("redirectPath");
		System.out.println(redirectPath);
		if (redirectPath == "null") {
			redirectPath = "/";
		}
		// 인가 code를 통해 카카오 OAuth Token 발급 및 Access Token 추출
		log.info("인가 code를 통해 카카오 OAuth Token 발급");
		String accessToken = kakaoService.getKakaoAccessToken(code);
		// 추출한 Access Token을 통해 유저 정보 요청
		log.info("추출한 Access Token을 통해 유저 정보 요청");
		// 유저 정보를 기반으로 회원가입 & 로그인 처리
		log.info("유저 정보를 기반으로 회원가입 & 로그인 처리");
		memberDomain member;
		member = kakaoService.getKakaoProfile(accessToken);
		
		session.setAttribute("user_info", member);

		return "redirect:" + redirectPath;
	}
}
