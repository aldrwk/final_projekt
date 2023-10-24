package com.spring.final_project.payment;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PayHistoryDomain {

	int payNum, payerNum, reservNum;
	String payMethod, PayPrice;
	String cardNum = "0000-0000-0000-0000";
	LocalDateTime payDate;

}
