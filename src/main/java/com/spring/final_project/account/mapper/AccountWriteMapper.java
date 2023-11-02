package com.spring.final_project.account.mapper;

import com.spring.final_project.account.AccountDomain;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface AccountWriteMapper {

	public int insert(AccountDomain account);

	public int update(AccountDomain account);



}
