package com.spring.final_project.host;

import lombok.Data;

import java.sql.*;
import java.time.LocalDateTime;

@Data
public class hostDomain {

	int hostNum, memberNum;
	String hostname;
	String hostProfile = "/image/host/host_profile.webp";
	String intro = "";

	LocalDateTime createHostDate, changeHostDate;
}
