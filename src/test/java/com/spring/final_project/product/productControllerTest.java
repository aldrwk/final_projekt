package com.spring.final_project.product;

import org.junit.jupiter.api.Test;

import java.text.NumberFormat;
import java.util.*;

class productControllerTest {

	@Test
	void productRegist() {
			Locale locale = new Locale("ko", "KR"); // 한국 로케일 (한국어, 대한민국)
			NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);
			String newPrice = String.valueOf(numberFormat.format(Integer.parseInt("50000")));
		System.out.println(newPrice);

	}
}