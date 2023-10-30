package com.spring.final_project.api.kakao;

import com.spring.final_project.payment.PaymentService;
import com.spring.final_project.product.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static com.spring.final_project.api.util.ApiConfig.*;

@Service
public class KakaoPayService {
	private static final Logger log = LoggerFactory.getLogger(KakaoLoginService.class);

	KakaoPayReadyDto kakaoPayReadyDto;
	private ProductOptionService productOptionService;
	private PaymentService paymentService;

	@Autowired
	public KakaoPayService(ProductOptionService productOptionService, PaymentService paymentService) {
		this.productOptionService = productOptionService;
		this.paymentService = paymentService;
	}

	@Transactional
	public KakaoPayReadyDto kakaoPayReady(ProductDomain product, ProductOptionDomain option, String quantity, String totalPrice, String partnerOrderId) {
		int optionId = option.getOptionId();
		int restCheckResult = productOptionService.restCheck(optionId, quantity);
//		int intQuantity = Integer.parseInt(quantity);
		log.info("재고 체크 : "+ restCheckResult);

		if (restCheckResult > 0) {
//			Map<String, Object> restDownMap = new HashMap<>();
//			restDownMap.put("optionId", optionId);
//			restDownMap.put("quantity", intQuantity);
//			productOptionService.restDown(restDownMap);
			// 카카오페이 요청 양식
			MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
			parameters = setPayReadyParmeter(parameters, product, option, quantity, totalPrice, partnerOrderId);
			// 파라미터, 헤더
			HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());
			// 외부에 보낼 url
			RestTemplate restTemplate = new RestTemplate();
			kakaoPayReadyDto = restTemplate.postForObject(
					"https://kapi.kakao.com/v1/payment/ready",
					requestEntity,
					KakaoPayReadyDto.class);
			log.info(kakaoPayReadyDto.toString());
			return kakaoPayReadyDto;
		} else {
			return kakaoPayReadyDto;
		}

	}

	public KakaoApproveResponse ApproveResponse(String pgToken, String partnerOrderId) {
		// 카카오 요청
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.add("cid", cid);
		parameters.add("tid", kakaoPayReadyDto.getTid());
		parameters.add("partner_order_id", partnerOrderId);
		parameters.add("partner_user_id", "Free");
		parameters.add("pg_token", pgToken);

		// 파라미터, 헤더
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

		// 외부에 보낼 url
		RestTemplate restTemplate = new RestTemplate();

		KakaoApproveResponse approveResponse = restTemplate.postForObject(
				"https://kapi.kakao.com/v1/payment/approve",
				requestEntity,
				KakaoApproveResponse.class);

		return approveResponse;
	}

	private HttpHeaders getHeaders() {
		HttpHeaders httpHeaders = new HttpHeaders();

		String auth = "KakaoAK " + ADMIN_KEY;

		httpHeaders.set("Authorization", auth);
		httpHeaders.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		return httpHeaders;
	}

	private static MultiValueMap<String, String> setPayReadyParmeter(MultiValueMap<String, String> parameters, ProductDomain product, ProductOptionDomain option, String quantity, String totalPrice, String partnerOrderId) {
		final String ZERO = "0";
		String productName = product.getTitle();
		String optionName = option.getOptionName();
		String itemName = productName + " " + optionName;

		parameters.add("cid", cid);
		parameters.add("partner_order_id", partnerOrderId);
		parameters.add("partner_user_id", "Free");
		parameters.add("item_name", itemName);
		parameters.add("quantity", quantity);
		parameters.add("total_amount", totalPrice);
		parameters.add("vat_amount", ZERO);
		parameters.add("tax_free_amount", ZERO);
		parameters.add("approval_url", APPROVAL_URL); // 성공 시 redirect url
		parameters.add("cancel_url", CANCEL_URL); // 취소 시 redirect url
		parameters.add("fail_url", FAIL_URL); // 실패 시 redirect url

		return parameters;
	}
}
