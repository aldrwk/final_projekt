package com.spring.final_project.api.util;

public class ApiConfig {

//KAKAO
	public final static String KAKAO_REST_API_KEY = "cfba8202c491dd4272811ab220211412";
	public final static String KAKAO_REDIRECT_URI = "http://localhost/api/login/kakao";

	public final static String KAKAO_LOGIN_URL = String.format("redirect:https://kauth.kakao.com/oauth/authorize?client_id=%s&redirect_uri=%s&response_type=code",
			KAKAO_REST_API_KEY, KAKAO_REDIRECT_URI);
	public static final String cid = "TC0ONETIME"; // 가맹점 테스트 코드
	public static final String ADMIN_KEY = "6e0bb1b64847c937a7f4ad3a6414b04e";
	public static final String APPROVAL_URL = "http://localhost/api/pay/success"; // 성공 시 redirect url
	public static final String CANCEL_URL = "http://localhost/pay/cancel"; // 취소 시 redirect url
	public static final String FAIL_URL = "http://localhost/pay/fail";


	//NAVER
	public final static String NAVER_FROM_NUMBER = "01085957675";
	public final static String NAVER_ACCESSKEY = "tBaczgc0CzXIagsA6uh4";
	// 2차 인증을 위해 서비스마다 할당되는 service secret
	public final static String NAVER_SECRETKEY = "w9V6Yo0i4D1HXLf3sZQYHP4OzgIdJEyaEJJp0Mlz";
	// 프로젝트에 할당된 SMS 서비스 ID
	public final static String NAVER_SERVICEID = "ncp:sms:kr:316028741712:free_sms";

	public final static String NAVER_SMS_URL = String.format("https://sens.apigw.ntruss.com/sms/v2/services/%s/messages",
			NAVER_SERVICEID);
	public final static String NAVER_SMS_SIGNATURE_URL = String.format("/sms/v2/services/%s/messages",
	NAVER_SERVICEID);


}
