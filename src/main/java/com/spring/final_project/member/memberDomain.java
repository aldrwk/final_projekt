package com.spring.final_project.member;

import lombok.Data;

import java.sql.*;

@Data
public class memberDomain {

	int num;

	String email, password, name, mobile_num, profile, Authorization;

	Timestamp create_date, change_date;


}
