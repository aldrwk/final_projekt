package com.spring.final_project.account;

import org.springframework.stereotype.Service;

@Service
public interface accountService {

	public accountDomain findById(int hostNum);

	public int insert(accountDomain account);

	public int update(accountDomain account);

}
