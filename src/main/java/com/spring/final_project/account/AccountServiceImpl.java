package com.spring.final_project.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

	private AccountMapper accountMapper;

	@Autowired
	public AccountServiceImpl(AccountMapper accountMapper) {
		this.accountMapper = accountMapper;
	}

	@Override
	public int insert(AccountDomain account) {
		return accountMapper.insert(account);
	}

	@Override
	public int update(AccountDomain account) {
		return accountMapper.update(account);
	}

	@Override
	public AccountDomain findById(int hostNum) {
		return accountMapper.findById(hostNum);
	}
}
