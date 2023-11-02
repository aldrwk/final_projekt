package com.spring.final_project.account;

import com.spring.final_project.account.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	private AccountWriteMapper accountWriteMapper;
	private AccountReadMapper accountReadMapper;

	@Autowired
	public AccountServiceImpl(AccountWriteMapper accountWriteMapper, AccountReadMapper accountReadMapper) {
		this.accountWriteMapper = accountWriteMapper;
		this.accountReadMapper = accountReadMapper;
	}

	@Override
	public int insert(AccountDomain account) {
		return accountWriteMapper.insert(account);
	}

	@Override
	public int update(AccountDomain account) {
		return accountWriteMapper.update(account);
	}

	@Override
	@Transactional(readOnly = true)
	public AccountDomain findById(int hostNum) {
		return accountReadMapper.findById(hostNum);
	}
}
