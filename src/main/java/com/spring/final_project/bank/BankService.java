package com.spring.final_project.bank;

import java.util.List;

public interface BankService {

	public List<BankDomain> findAll();

	public BankDomain findByBank(String bankName);
}
