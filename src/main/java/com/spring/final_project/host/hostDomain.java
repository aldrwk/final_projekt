package com.spring.final_project.host;

import lombok.Data;

import java.sql.*;

@Data
public class hostDomain {

	int host_num, member_num;
	String host_profile, intro;

	Timestamp create_host_date, change_host_date;
}
