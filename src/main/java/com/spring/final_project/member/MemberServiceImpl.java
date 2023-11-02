package com.spring.final_project.member;

import com.spring.final_project.member.mapper.MemberReadMapper;
import com.spring.final_project.member.mapper.MemberWriteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.spring.final_project.util.Messages.FAIL;
import static com.spring.final_project.util.Messages.SUCCESS;

@Service
public class MemberServiceImpl implements MemberService {
	private MemberWriteMapper memberWriteMapper;
	private MemberReadMapper memberReadMapper;

	@Autowired
	public MemberServiceImpl(MemberWriteMapper memberWriteMapper, MemberReadMapper memberReadMapper) {
		this.memberWriteMapper = memberWriteMapper;
		this.memberReadMapper = memberReadMapper;
	}

	@Override
	@Transactional
	public int insert(MemberDomain member) {
		return memberWriteMapper.insert(member) == SUCCESS ? SUCCESS:FAIL;
	}


	@Override
	@Transactional(readOnly = true)
	public MemberDomain findById(String email) {
		return memberReadMapper.findById(email);
	}

	@Override
	@Transactional(readOnly = true)
	public MemberDomain login(String email) {
		return memberReadMapper.login(email);
	}
	@Override
	@Transactional(readOnly = true)
	public MemberDomain findByMobile(String mobile) {
		return memberReadMapper.findByMobile(mobile);
	}

	@Override
	@Transactional(readOnly = true)
	public MemberDomain findByNum(int memberNum) {
		return memberReadMapper.findByNum(memberNum);
	}

	@Override
	@Transactional
	public int updatePassword(MemberDomain member) {
		return memberWriteMapper.updatePassword(member);
	}

	@Override
	@Transactional
	public int updateAuth(MemberDomain member) {
		return memberWriteMapper.updateAuth(member);
	}

	@Override
	@Transactional
	public int updateInfo(MemberDomain member) {
		return memberWriteMapper.updateInfo(member);
	}
}
