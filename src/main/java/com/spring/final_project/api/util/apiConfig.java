package com.spring.final_project.api.util;

public class apiConfig {

	public final static String KAKAO_REST_API_KEY = "cfba8202c491dd4272811ab220211412";
	public final static String KAKAO_REDIRECT_URI = "http://localhost/api/login/kakao";

	public final static String KAKAO_LOGIN_URL = String.format("redirect:https://kauth.kakao.com/oauth/authorize?client_id=%s&redirect_uri=%s&response_type=code",
			KAKAO_REST_API_KEY, KAKAO_REDIRECT_URI);

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
