package com.spring.final_project.payment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceImplTest {

	PayHistoryMapper payHistoryMapper;



	@Test
	void totalProfit() {
		int hostNum = 1;
		System.out.println(payHistoryMapper.totalProfit(hostNum));
	}
}