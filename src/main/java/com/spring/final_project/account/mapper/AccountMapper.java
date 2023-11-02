package com.spring.final_project.account.mapper;

import com.spring.final_project.account.AccountDomain;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
public interface AccountMapper {

	public AccountDomain findById(int hostNum);

	public int insert(AccountDomain account);

	public int update(AccountDomain account);



}
