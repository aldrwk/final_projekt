package com.spring.final_project.api.kakao;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import static com.spring.final_project.api.util.apiConfig.ADMIN_KEY;
import static com.spring.final_project.api.util.apiConfig.cid;
import static org.junit.jupiter.api.Assertions.*;

class KakaoLoginServiceTest {

	@Test
	private HttpHeaders getHeaders() {
		HttpHeaders httpHeaders = new HttpHeaders();

		String auth = "KakaoAK " + ADMIN_KEY;

		httpHeaders.set("Authorization", auth);
		httpHeaders.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		return httpHeaders;
	}
	@Test
	void kakaoPayReady() {
		kakaoPayReadyDto kakaoPayReadyDto = new kakaoPayReadyDto();
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
		parameters.add("approval_url", "http://localhost"); // 성공 시 redirect url
		parameters.add("cancel_url", "http://localhost"); // 취소 시 redirect url
		parameters.add("fail_url", "http://localhost"); // 실패 시 redirect url

		// 파라미터, 헤더
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

		// 외부에 보낼 url
		RestTemplate restTemplate = new RestTemplate();

//		kakaoPayReadyDto = restTemplate.postForObject(
//				"https://kapi.kakao.com/v1/payment/ready",
//				requestEntity,
//				kakaoPayReadyDto.class);
		System.out.println(restTemplate.postForObject(
				"https://kapi.kakao.com/v1/payment/ready",
				requestEntity,
				kakaoPayReadyDto.class));
//		System.out.println(kakaoPayReadyDto);
	}
}