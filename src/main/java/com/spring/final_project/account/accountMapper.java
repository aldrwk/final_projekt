package com.spring.final_project.account;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface accountMapper {

	public accountDomain findById(int hostNum);

	public int insert(accountDomain account);

	public int update(accountDomain account);



}
