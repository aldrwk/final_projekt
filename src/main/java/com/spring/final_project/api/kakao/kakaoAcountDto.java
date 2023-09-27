package com.spring.final_project.api.kakao;

import lombok.*;
import org.springframework.stereotype.Repository;

@Getter
@Setter
@Repository
public class kakaoAcountDto {
	long id;
	String nickname, profile, email, phoneNumber, accessToken;
}