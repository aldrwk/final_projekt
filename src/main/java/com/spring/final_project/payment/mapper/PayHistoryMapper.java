package com.spring.final_project.payment.mapper;

import com.spring.final_project.payment.PayHistoryDomain;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PayHistoryMapper {

	public int insert(PayHistoryDomain payHistoryDomain);


	public Integer totalProfit(int hostNum);
	public Integer profitInThisMonth(Map<String, Object> map);
	public Integer countPay(int hostNum);
}
