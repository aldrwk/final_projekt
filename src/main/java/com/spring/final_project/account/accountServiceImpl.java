package com.spring.final_project.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class accountServiceImpl implements accountService{

	private accountMapper accountMapper;

	@Autowired
	public accountServiceImpl(com.spring.final_project.account.accountMapper accountMapper) {
		this.accountMapper = accountMapper;
	}

	@Override
	public int insert(accountDomain account) {
		return accountMapper.insert(account);
	}

	@Override
	public int update(accountDomain account) {
		return accountMapper.update(account);
	}

	@Override
	public accountDomain findById(int hostNum) {
		return accountMapper.findById(hostNum);
	}
}
