package com.spring.final_project.api.kakao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import static com.spring.final_project.api.util.apiConfig.ADMIN_KEY;
import static com.spring.final_project.api.util.apiConfig.cid;

@Service
public class KakaoPayService {
	private static final Logger log = LoggerFactory.getLogger(KakaoLoginService.class);

	kakaoPayReadyDto kakaoPayReadyDto;


	public kakaoPayReadyDto kakaoPayReady() {
		// 카카오페이 요청 양식
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.add("cid", cid);
		parameters.add("partner_order_id", "가맹점 주문 번호");
		parameters.add("partner_user_id", "가맹점 회원 ID");
		parameters.add("item_name", "상품명");
		parameters.add("quantity", "1");
		parameters.add("total_amount", "10000");
		parameters.add("vat_amount", "0");
		parameters.add("tax_free_amount", "0");
		parameters.add("approval_url", "http://localhost/api/pay/success"); // 성공 시 redirect url
		parameters.add("cancel_url", "http://localhost/api/pay/cancel"); // 취소 시 redirect url
		parameters.add("fail_url", "http://localhost/api/pay/fail"); // 실패 시 redirect url

		// 파라미터, 헤더
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

		// 외부에 보낼 url
		RestTemplate restTemplate = new RestTemplate();


		kakaoPayReadyDto = restTemplate.postForObject(
				"https://kapi.kakao.com/v1/payment/ready",
				requestEntity,
				kakaoPayReadyDto.class);
		log.info(kakaoPayReadyDto.toString());
		return kakaoPayReadyDto;
	}

	public KakaoApproveResponse ApproveResponse(String pgToken) {
		// 카카오 요청
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.add("cid", cid);
		parameters.add("tid", kakaoPayReadyDto.getTid());
		parameters.add("partner_order_id", "가맹점 주문 번호");
		parameters.add("partner_user_id", "가맹점 회원 ID");
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
}
