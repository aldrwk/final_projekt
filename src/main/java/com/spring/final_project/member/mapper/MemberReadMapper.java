package com.spring.final_project.member.mapper;

import com.spring.final_project.member.MemberDomain;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberReadMapper {

	public MemberDomain findById(String email);

	public MemberDomain login(String email);
	public MemberDomain findByMobile(String mobile);
	public MemberDomain findByNum(int memberNum);


}
