package com.spring.final_project.bank;

import com.spring.final_project.bank.mapper.BankMapper;
import com.spring.final_project.bank.mapper.BankReadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankServiceImpl implements BankService {

	private BankReadMapper bankReadMapper;

	@Autowired
	public BankServiceImpl(BankMapper bankMapper) {
		this.bankReadMapper = bankReadMapper;
	}

	@Override
	public BankDomain findByBank(String bankName) {
		return bankReadMapper.findByBank(bankName);
	}

	@Override
	public List<BankDomain> findAll() {
		return bankReadMapper.findAll();
	}
}
