package com.spring.final_project.api.kakao;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class KakaoPayReadyDto {

	String tid; // 결제 고유 번호
	String next_redirect_mobile_url; // 모바일 웹일 경우 받는 결제페이지 url
	String next_redirect_pc_url; // pc 웹일 경우 받는 결제 페이지
	String created_at;
}
