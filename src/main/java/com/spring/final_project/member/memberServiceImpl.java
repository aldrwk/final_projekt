package com.spring.final_project.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class memberServiceImpl implements memberService {
	private memberMapper memberMapper;

	@Autowired
	public memberServiceImpl(memberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}


	@Override
	public int insert(memberDomain member) {


		return memberMapper.insert(member) == 1 ? 1:0;
	}


	@Override
	public memberDomain findById(String email) {
		return memberMapper.findById(email);
	}
	@Override
	public memberDomain findByMobile(String mobile) {
		return memberMapper.findByMobile(mobile);
	}

	@Override
	public int updatePassword(memberDomain member) {
		return memberMapper.updatePassword(member);
	}

	@Override
	public int updateAuth(memberDomain member) {
		return memberMapper.updateAuth(member);
	}

	@Override
	public int updateInfo(memberDomain member) {
		return memberMapper.updateInfo(member);
	}
}
