package com.spring.final_project.payment.mapper;

import com.spring.final_project.payment.PayHistoryDomain;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface PayHistoryReadMapper {

	public Integer totalProfit(int hostNum);
	public Integer profitInThisMonth(Map<String, Object> map);
	public Integer countPay(int hostNum);
}
