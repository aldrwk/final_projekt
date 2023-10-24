package com.spring.final_project.product;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductDomain {
	int producNum,hostNum, secCateNum;

	String title, content, address, addressDetail, area, areaDetail, thumnail;

	LocalDateTime producRegitDate, producUpdateDate;

	int viewCount = 0;
}
