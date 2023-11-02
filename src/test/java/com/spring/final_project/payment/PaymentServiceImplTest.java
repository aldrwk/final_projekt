package com.spring.final_project.payment;

import com.spring.final_project.payment.mapper.PayHistoryMapper;
import org.junit.jupiter.api.Test;

class PaymentServiceImplTest {

	PayHistoryMapper payHistoryMapper;



	@Test
	void totalProfit() {
		int hostNum = 1;
		System.out.println(payHistoryMapper.totalProfit(hostNum));
	}
}