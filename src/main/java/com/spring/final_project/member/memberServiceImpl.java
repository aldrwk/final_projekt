package com.spring.final_project.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.spring.final_project.util.messages.FAIL;
import static com.spring.final_project.util.messages.SUCCESS;

@Service
public class memberServiceImpl implements memberService {
	private memberMapper memberMapper;

	@Autowired
	public memberServiceImpl(memberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}


	@Override
	@Transactional
	public int insert(memberDomain member) {
		return memberMapper.insert(member) == SUCCESS ? SUCCESS:FAIL;
	}


	@Override
	@Transactional
	public memberDomain findById(String email) {
		return memberMapper.findById(email);
	}

	@Override
	@Transactional
	public memberDomain login(String email) {
		return memberMapper.login(email);
	}
	@Override
	@Transactional
	public memberDomain findByMobile(String mobile) {
		return memberMapper.findByMobile(mobile);
	}

	@Override
	@Transactional
	public memberDomain findByNum(int memberNum) {
		return memberMapper.findByNum(memberNum);
	}

	@Override
	@Transactional
	public int updatePassword(memberDomain member) {
		return memberMapper.updatePassword(member);
	}

	@Override
	@Transactional
	public int updateAuth(memberDomain member) {
		return memberMapper.updateAuth(member);
	}

	@Override
	@Transactional
	public int updateInfo(memberDomain member) {
		return memberMapper.updateInfo(member);
	}
}
