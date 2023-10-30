package com.spring.final_project.payment;

import com.spring.final_project.product.ProductOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {

	PayHistoryMapper payHistoryMapper;
	ProductOptionService productOptionService;
	final static int NO_REST = 0;
	final static int REST = 1;

	public PaymentServiceImpl(ProductOptionService productOptionService) {
		this.productOptionService = productOptionService;
	}

	@Autowired
	public PaymentServiceImpl(PayHistoryMapper payHistoryMapper, ProductOptionService productOptionService) {
		this.payHistoryMapper = payHistoryMapper;
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
		return payHistoryMapper.insert(payHistoryDomain);
	}

	@Override
	@Transactional
	public int totalProfit(int hostNum) {
		return payHistoryMapper.totalProfit(hostNum);
	}

	@Override
	@Transactional
	public int profitInThisMonth(Map<String, Object> map) {
		return payHistoryMapper.profitInThisMonth(map);
	}

	@Override
	@Transactional
	public Integer countPay(int hostNum) {
		return payHistoryMapper.countPay(hostNum);
	}
}
