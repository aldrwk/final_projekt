package com.spring.final_project.api.kakao;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class KakaoService {


	private kakaoAcountDto kakaoAcountDto;

	@Autowired
	public KakaoService(kakaoAcountDto kakaoAcountDto) {
		this.kakaoAcountDto = kakaoAcountDto;
	}

	public String getKakaoAccessToken(String code) {

		String REST_API_KEY = "cfba8202c491dd4272811ab220211412";
		String REDIRECT_URI = "http://localhost/api/login/kakao";

		// POST방식으로 key=value 데이터 요청
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// HttpBody 오브젝트 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", REST_API_KEY);
		params.add("redirect_uri", REDIRECT_URI);
		params.add("code", code);

		// HttpHeader와 HttpBody를 하나의 오브젝트로 담는다
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

		// 실제요청
		ResponseEntity<String> response = restTemplate.exchange(
				"https://kauth.kakao.com/oauth/token",
				HttpMethod.POST,
				kakaoTokenRequest,
				String.class
		);

		// 응답 정보 출력
		System.out.println("response : ");
		System.out.println(response);

		// JSON -> 액세스 토큰 파싱
		String tokenJson = response.getBody();
		JSONObject rjson = new JSONObject(tokenJson);
		return rjson.getString("access_token");

	}

	// 카카오 액세스 토큰을 사용해 유저 정보 요청
	public kakaoAcountDto getKakaoProfile(String accessToken) {

		///유저정보 요청
		RestTemplate restTemplate = new RestTemplate();

		//HttpHeader
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + accessToken);
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		//HttpHeader와 HttpBody 담기
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers);

		ResponseEntity<String> response = restTemplate.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.POST, kakaoProfileRequest, String.class);

		// 응답 JSON에서 필요한 정보들을 추출 후 DTO에 담기
		JSONObject body = new JSONObject(response.getBody());
		long id = body.getLong("id");
		String email = body.getJSONObject("kakao_account").getString("email");
		String Phone_number = body.getJSONObject("kakao_account").getString("phone_number");
		String nickname = body.getJSONObject("properties").getString("nickname");
		String profile = body.getJSONObject("properties").getString("thumbnail_image");
		kakaoAcountDto.setId(id);
		kakaoAcountDto.setEmail(email);
		kakaoAcountDto.setNickname(nickname);
		kakaoAcountDto.setProfile(profile);
		kakaoAcountDto.setPhoneNumber(Phone_number);
		kakaoAcountDto.setAccessToken(accessToken);

		return kakaoAcountDto;
	}
	public void logoutProc(kakaoAcountDto kakaoAcountDto) {
		String accessToken = kakaoAcountDto.getAccessToken();

		// POST방식으로 key=value 데이터 요청
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded");
		headers.add("Authorization", "Bearer "+accessToken);

		// HttpHeader와 HttpBody를 하나의 오브젝트로 담는다
		HttpEntity<String> kakaoTokenRequest = new HttpEntity<>(headers);

		// 실제요청
		ResponseEntity<String> response = restTemplate.exchange(
				"https://kapi.kakao.com/v1/user/logout",
				HttpMethod.POST,
				kakaoTokenRequest,
				String.class
		);

		System.out.println("response : ");
		System.out.println(response);
	}

}
