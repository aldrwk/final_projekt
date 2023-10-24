package com.spring.final_project.account;

import org.springframework.stereotype.Service;

@Service
public interface AccountService {

	public AccountDomain findById(int hostNum);

	public int insert(AccountDomain account);

	public int update(AccountDomain account);

}
