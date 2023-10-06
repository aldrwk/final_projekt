package com.spring.final_project.api.kakao;

import lombok.*;
import org.springframework.stereotype.Repository;

@Data
@Repository
public class kakaoAcountDto {
	long id;
	String name, profile, email, mobile_num, accessToken;
}
