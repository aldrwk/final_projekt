package com.spring.final_project.account;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class accountDomain {

	int accountId, hostNum;
	String accountNum, bankCode, accountOwner;
	LocalDateTime changeDate;
}
