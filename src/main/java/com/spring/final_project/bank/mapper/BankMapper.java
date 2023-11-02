package com.spring.final_project.bank.mapper;

import com.spring.final_project.bank.BankDomain;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BankMapper {

	public List<BankDomain> findAll();

	public BankDomain findByBank(String bankName);

}
