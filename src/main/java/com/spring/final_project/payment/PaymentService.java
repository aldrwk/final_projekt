package com.spring.final_project.payment;


import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface PaymentService {

	public PayHistoryDomain setPayHistroy(int memberNum, int reservNum,String payMethod, String payPrice, String payDate);

	public int restCheck(int optionId, String quantity);
	public int insert(PayHistoryDomain payHistoryDomain);

	public int totalProfit(int hostNum);

	public int profitInThisMonth(Map<String, Object> map);

	public Integer countPay(int hostNum);



}
