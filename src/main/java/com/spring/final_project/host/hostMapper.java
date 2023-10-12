package com.spring.final_project.host;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

@Mapper
public interface hostMapper {

	public int insert(hostDomain host);

	public hostDomain findById(String email);

	public int updateInfo(hostDomain host);
}
