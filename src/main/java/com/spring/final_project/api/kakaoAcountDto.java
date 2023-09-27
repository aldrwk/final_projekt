package com.spring.final_project.api;

import lombok.*;
import org.springframework.stereotype.Repository;

@Getter
@Setter
@Repository
public class kakaoAcountDto {
	long id;
	String nickname, profile, email, phoneNumber, accessToken;
}
