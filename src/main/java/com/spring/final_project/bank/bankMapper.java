package com.spring.final_project.bank;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface bankMapper {

	public List<bankDomain> findAll();

	public bankDomain findByBank(String bankName);

}
