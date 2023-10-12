package com.spring.final_project.member;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

@Mapper
public interface memberMapper {

	public int insert(memberDomain member);

	public memberDomain findById(String email);
	public memberDomain login(String email);

	public memberDomain findByMobile(String mobile);

	public memberDomain findByNum(int memberNum);

	public int updatePassword(memberDomain member);
	public int updateAuth(memberDomain member);
	public int updateInfo(memberDomain member);

}
