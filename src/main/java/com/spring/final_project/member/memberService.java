package com.spring.final_project.member;

import com.spring.final_project.member.memberDomain;
import org.springframework.stereotype.Service;

public interface memberService {

	public int insert(memberDomain member);

	public memberDomain findById(String email);

	public memberDomain findByMobile(String mobile);

	public int updatePassword(memberDomain member);
}
