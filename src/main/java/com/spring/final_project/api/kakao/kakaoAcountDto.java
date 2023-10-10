package com.spring.final_project.api.kakao;

import lombok.*;
import org.springframework.stereotype.Repository;

@Data
@Repository
public class kakaoAcountDto {
	String name, profile, email, mobileNum, accessToken;
}
