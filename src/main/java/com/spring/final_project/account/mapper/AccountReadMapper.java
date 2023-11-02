package com.spring.final_project.account.mapper;

import com.spring.final_project.account.AccountDomain;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface AccountReadMapper {

	public AccountDomain findById(int hostNum);




}
