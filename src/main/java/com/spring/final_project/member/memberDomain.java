package com.spring.final_project.member;

import lombok.Data;

import java.sql.*;
import java.time.LocalDateTime;

@Data
public class memberDomain {

	int num;

	String email, password, name, profile, mobileNum ;

	String authorization = "member";

	String accountType = "일반회원 계정";


	LocalDateTime createDate, changeDate;


}
