package com.spring.final_project.bank;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class bankServiceImpl implements bankService {

	private bankMapper bankMapper;

	public bankServiceImpl(bankMapper bankMapper) {
		this.bankMapper = bankMapper;
	}

	@Override
	public bankDomain findByBank(String bankName) {
		return bankMapper.findByBank(bankName);
	}

	@Override
	public List<bankDomain> findAll() {
		return bankMapper.findAll();
	}
}
