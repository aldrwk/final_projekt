package com.spring.final_project.bank;

import java.util.List;

public interface bankService {

	public List<bankDomain> findAll();
	public bankDomain findByBank(String bankName);
}
