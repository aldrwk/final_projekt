package com.spring.final_project.host.mapper;


import com.spring.final_project.host.HostDomain;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HostReadMapper {

	public HostDomain findById(String email);

	public HostDomain findByHostNum(int hostNum);

}
