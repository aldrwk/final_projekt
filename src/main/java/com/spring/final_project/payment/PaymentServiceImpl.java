package com.spring.final_project.payment;

import com.spring.final_project.payment.mapper.*;
import com.spring.final_project.product.ProductOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {

	PayHistoryWriteMapper payHistoryWriteMapper;
	PayHistoryReadMapper payHistoryReadMapper;
	ProductOptionService productOptionService;
	final static int NO_REST = 0;
	final static int REST = 1;


	@Autowired
	public PaymentServiceImpl(PayHistoryWriteMapper payHistoryWriteMapper, PayHistoryReadMapper payHistoryReadMapper, ProductOptionService productOptionService) {
		this.payHistoryWriteMapper = payHistoryWriteMapper;
		this.payHistoryReadMapper = payHistoryReadMapper;
		this.productOptionService = productOptionService;
	}

	@Override
	public PayHistoryDomain setPayHistroy(int memberNum, int reservNum, String payMethod, String payPrice, String payDate) {
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
		return payHistoryWriteMapper.insert(payHistoryDomain);
	}

	@Override
	@Transactional(readOnly = true)
	public int totalProfit(int hostNum) {
		return payHistoryReadMapper.totalProfit(hostNum);
	}

	@Override
	@Transactional(readOnly = true)
	public int profitInThisMonth(Map<String, Object> map) {
		return payHistoryReadMapper.profitInThisMonth(map);
	}

	@Override
	@Transactional(readOnly = true)
	public Integer countPay(int hostNum) {
		return payHistoryReadMapper.countPay(hostNum);
	}
}
