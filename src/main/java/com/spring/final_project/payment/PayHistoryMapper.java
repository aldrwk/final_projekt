package com.spring.final_project.payment;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PayHistoryMapper {

	public int insert(PayHistoryDomain payHistoryDomain);

}
