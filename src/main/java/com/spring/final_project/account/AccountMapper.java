package com.spring.final_project.account;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {

	public AccountDomain findById(int hostNum);

	public int insert(AccountDomain account);

	public int update(AccountDomain account);



}
