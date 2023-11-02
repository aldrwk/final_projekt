package com.spring.final_project.member.mapper;

import com.spring.final_project.member.MemberDomain;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberWriteMapper {

	public int insert(MemberDomain member);

	public int updatePassword(MemberDomain member);
	public int updateAuth(MemberDomain member);
	public int updateInfo(MemberDomain member);

}
