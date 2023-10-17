package com.spring.final_project.bank;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankServiceImpl implements BankService {

	private BankMapper bankMapper;

	public BankServiceImpl(BankMapper bankMapper) {
		this.bankMapper = bankMapper;
	}

	@Override
	public BankDomain findByBank(String bankName) {
		return bankMapper.findByBank(bankName);
	}

	@Override
	public List<BankDomain> findAll() {
		return bankMapper.findAll();
	}
}
