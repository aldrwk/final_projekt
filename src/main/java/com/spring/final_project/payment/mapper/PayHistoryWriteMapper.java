package com.spring.final_project.payment.mapper;

import com.spring.final_project.payment.PayHistoryDomain;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface PayHistoryWriteMapper {

	public int insert(PayHistoryDomain payHistoryDomain);



}
