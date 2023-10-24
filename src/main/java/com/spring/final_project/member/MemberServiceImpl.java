package com.spring.final_project.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.spring.final_project.util.Messages.FAIL;
import static com.spring.final_project.util.Messages.SUCCESS;

@Service
public class MemberServiceImpl implements MemberService {
	private MemberMapper memberMapper;

	@Autowired
	public MemberServiceImpl(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}


	@Override
	@Transactional
	public int insert(MemberDomain member) {
		return memberMapper.insert(member) == SUCCESS ? SUCCESS:FAIL;
	}


	@Override
	@Transactional
	public MemberDomain findById(String email) {
		return memberMapper.findById(email);
	}

	@Override
	@Transactional
	public MemberDomain login(String email) {
		return memberMapper.login(email);
	}
	@Override
	@Transactional
	public MemberDomain findByMobile(String mobile) {
		return memberMapper.findByMobile(mobile);
	}

	@Override
	@Transactional
	public MemberDomain findByNum(int memberNum) {
		return memberMapper.findByNum(memberNum);
	}

	@Override
	@Transactional
	public int updatePassword(MemberDomain member) {
		return memberMapper.updatePassword(member);
	}

	@Override
	@Transactional
	public int updateAuth(MemberDomain member) {
		return memberMapper.updateAuth(member);
	}

	@Override
	@Transactional
	public int updateInfo(MemberDomain member) {
		return memberMapper.updateInfo(member);
	}
}
