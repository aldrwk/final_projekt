package com.spring.final_project.api.kakao;

import com.spring.final_project.member.memberDomain;
import com.spring.final_project.member.memberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.spring.final_project.api.util.apiConfig.KAKAO_LOGIN_URL;


@Controller
@RequestMapping("/api")
public class kakaoController {
	private static final Logger log = LoggerFactory.getLogger(kakaoController.class);
	private final KakaoLoginService kakaoLoginService;
	private kakaoAcountDto kakaoAcountDto;

	private memberService memberService;
	private KakaoPayService kakaoPayService;


	@Autowired
	public kakaoController(KakaoLoginService kakaoLoginService, kakaoAcountDto kakaoAcountDto, memberService memberService, KakaoPayService kakaoPayService) {
		this.kakaoLoginService = kakaoLoginService;
		this.kakaoAcountDto = kakaoAcountDto;
		this.memberService = memberService;
		this.kakaoPayService = kakaoPayService;
	}

	@GetMapping("/login/kakaoauth")
	public String kakaoLoginURL(HttpServletRequest request) {
		String redirectPath = request.getParameter("redirectPath");
		return KAKAO_LOGIN_URL;
	}

	@GetMapping("/login/kakao")
	public String kakaoCallBack(@RequestParam String code, HttpServletRequest request, kakaoAcountDto kakaoAcountDto) {
		HttpSession session = request.getSession();
		String redirectPath = (String) session.getAttribute("redirectPath");
		System.out.println(redirectPath);
		if (redirectPath == "null") {
			redirectPath = "/";
		}
		// 인가 code를 통해 카카오 OAuth Token 발급 및 Access Token 추출
		log.info("인가 code를 통해 카카오 OAuth Token 발급");
		String accessToken = kakaoLoginService.getKakaoAccessToken(code);
		// 추출한 Access Token을 통해 유저 정보 요청
		log.info("추출한 Access Token을 통해 유저 정보 요청");
		// 유저 정보를 기반으로 회원가입 & 로그인 처리
		log.info("유저 정보를 기반으로 회원가입 & 로그인 처리");
		memberDomain member;
		member = kakaoLoginService.getKakaoProfile(accessToken, kakaoAcountDto);
		session.setAttribute("user_info", member);

		return "redirect:" + redirectPath;
	}

	/**
	 * 결제요청
	 */
	@PostMapping("/pay/ready")
	public String readyToKakaoPay() {
		log.info("testestsetsetet");
		String redirectUrl = kakaoPayService.kakaoPayReady().getNext_redirect_pc_url();
		return "redirect:"+redirectUrl;
	}


	@GetMapping("/pay/success")
	public String afterPayRequest(@RequestParam("pg_token") String pgToken, Model model) {
		KakaoApproveResponse kakaoApprove = kakaoPayService.ApproveResponse(pgToken);
		model.addAttribute("payInfo", kakaoApprove);
		return "/test";
	}
	/**
	 * 결제 진행 중 취소
	 */
//취소	@GetMapping("/pay/cancel")
//	public void cancel() {
//
//		throw new BusinessLogicException(ExceptionCode.PAY_CANCEL);
//	}
//
//	/**
//	 * 결제 실패
//	 */
//	@GetMapping("/pay/fail")
//	public void fail() {
//
//		throw new BusinessLogicException(ExceptionCode.PAY_FAILED);
//	}
}

