package com.spring.final_project.host;


import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface hostMapper {

	public int insert(hostDomain host);
}
