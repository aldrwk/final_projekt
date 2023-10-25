package com.spring.final_project.payment;

import com.spring.final_project.api.kakao.KakaoApproveResponse;
import com.spring.final_project.api.kakao.KakaoPayService;
import com.spring.final_project.product.ProductOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class PaymentServiceImpl implements PaymentService {

	PayHistoryMapper payHistoryMapper;
	ProductOptionService productOptionService;
	final static int NO_REST = 0;
	final static int REST = 1;

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
	public int restCheck(int optionId, String quantity) {
		int rest = productOptionService.getRestById(optionId);
		int intQuantity = Integer.parseInt(quantity);
		return rest - intQuantity >= 0? REST:NO_REST;
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
