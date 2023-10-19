package com.spring.final_project.product;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class productDomain {
	int producNum,hostNum, secCateNum;

	String title, content, address, addressDetail, thumnail;

	LocalDateTime producRegitDate, producUpdateDate;
}
