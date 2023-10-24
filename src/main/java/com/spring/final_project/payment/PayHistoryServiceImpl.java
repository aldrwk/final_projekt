package com.spring.final_project.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class PayHistoryServiceImpl implements PayHistoryService {

	PayHistoryMapper payHistoryMapper;

	@Autowired
	public PayHistoryServiceImpl(PayHistoryMapper payHistoryMapper) {
		this.payHistoryMapper = payHistoryMapper;
	}

	@Override
	public PayHistoryDomain setPayHistroy(int memberNum,int reservNum,String payMethod, String payPrice, String payDate) {
		PayHistoryDomain payHistoryDomain = new PayHistoryDomain();
		LocalDateTime payTime = LocalDateTime.parse(payDate);
		payHistoryDomain.setPayerNum(memberNum);
		payHistoryDomain.setReservNum(reservNum);
		payHistoryDomain.setPayMethod(payMethod);
		payHistoryDomain.setPayPrice(payPrice);
		payHistoryDomain.setPayDate(payTime);

		return payHistoryDomain;
	}

	@Override
	@Transactional
	public int insert(PayHistoryDomain payHistoryDomain) {
		return payHistoryMapper.insert(payHistoryDomain);
	}

}
