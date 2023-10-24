package com.spring.final_project.product;

import lombok.Data;

@Data
public class ProductOptionDomain {

	int optionId, producNum, reservationId, validPerson, rest, maxRegisterPerOne;
	String optionName, price;
}
