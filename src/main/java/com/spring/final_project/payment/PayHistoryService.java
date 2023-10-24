package com.spring.final_project.payment;

import org.apache.ibatis.annotations.Mapper;


public interface PayHistoryService {

	public PayHistoryDomain setPayHistroy(int memberNum, int reservNum,String payMethod, String payPrice, String payDate);

	public int insert(PayHistoryDomain payHistoryDomain);

}
