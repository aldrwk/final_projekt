package com.spring.final_project.host;


import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HostMapper {

	public int insert(HostDomain host);

	public HostDomain findById(String email);

	public HostDomain findByHostNum(int hostNum);

	public int updateInfo(HostDomain host);
}
