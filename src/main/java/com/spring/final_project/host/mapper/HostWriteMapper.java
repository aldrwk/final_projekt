package com.spring.final_project.host.mapper;


import com.spring.final_project.host.HostDomain;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HostWriteMapper {

	public int insert(HostDomain host);

	public int updateInfo(HostDomain host);
}
