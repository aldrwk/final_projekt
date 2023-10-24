package com.spring.final_project.api.kakao;

import com.spring.final_project.member.MemberDomain;
import com.spring.final_project.member.MemberService;
import com.spring.final_project.product.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.spring.final_project.api.util.ApiConfig.KAKAO_LOGIN_URL;


@Controller
@RequestMapping("/api")
public class KakaoController {
	private static final Logger log = LoggerFactory.getLogger(KakaoController.class);
	private final KakaoLoginService kakaoLoginService;
	private KakaoAcountDto kakaoAcountDto;

	private MemberService memberService;
	private KakaoPayService kakaoPayService;
	private ProductOptionService productOptionService;


	@Autowired
	public KakaoController(KakaoLoginService kakaoLoginService, KakaoAcountDto kakaoAcountDto, MemberService memberService, KakaoPayService kakaoPayService, ProductOptionService productOptionService) {
		this.kakaoLoginService = kakaoLoginService;
		this.kakaoAcountDto = kakaoAcountDto;
		this.memberService = memberService;
		this.kakaoPayService = kakaoPayService;
		this.productOptionService = productOptionService;
	}

	@GetMapping("/login/kakaoauth")
	public String kakaoLoginURL(HttpServletRequest request) {
		String redirectPath = request.getParameter("redirectPath");
		return KAKAO_LOGIN_URL;
	}

	@GetMapping("/login/kakao")
	public String kakaoCallBack(@RequestParam String code, HttpServletRequest request, KakaoAcountDto kakaoAcountDto) {
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
		MemberDomain member;
		member = kakaoLoginService.getKakaoProfile(accessToken, kakaoAcountDto);
		session.setAttribute("user_info", member);

		return "redirect:" + redirectPath;
	}

	/**
	 * 결제요청
	 */
	@PostMapping("/pay/ready")
	public String readyToKakaoPay(HttpSession session, int optionId, String quantity, String totalPrice) {
		ProductOptionDomain option = productOptionService.optionsById(optionId);
		ProductDomain product = (ProductDomain) session.getAttribute("product");
		MemberDomain member = (MemberDomain) session.getAttribute("user_info");
		String partnerOrderId = member.getNum()+"_"+product.getProducNum() + "_" + option.getOptionId();
		String redirectUrl = kakaoPayService.kakaoPayReady(product, option, quantity,totalPrice, partnerOrderId).getNext_redirect_pc_url();
		session.setAttribute("partnerOrderId",partnerOrderId);
		session.setAttribute("optionId", optionId);
		return "redirect:" + redirectUrl;
	}


	@GetMapping("/pay/success")
	public String afterPayRequest(@RequestParam("pg_token") String pgToken, Model model, HttpSession session) {
		int optionId = (int) session.getAttribute("optionId");
		String partnerOrderId = (String) session.getAttribute("partnerOrderId");
		log.info("옵션 아이디 " +optionId);
		int rest = productOptionService.getRestById(optionId);
		if (rest > 0) {
			productOptionService.restDown(optionId);
			KakaoApproveResponse kakaoApprove = kakaoPayService.ApproveResponse(pgToken,partnerOrderId);
			session.setAttribute("payInfo", kakaoApprove);
		}
		log.info(session.getAttribute("payInfo").toString());
		return "redirect:/reservation/result";
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

