package com.spring.final_project.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface memberMapper {

	public int insert(memberDomain member);

	public memberDomain findById(String email);

	public memberDomain findByMobile(String mobile);

	public int updatePassword(memberDomain member);
}
