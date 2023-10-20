package com.spring.final_project.host;

public interface hostService {

	public int insert(hostDomain host);

	public hostDomain findById(String email);

	public hostDomain findByHostNum(int hostNum);
	public int updateInfo(hostDomain host);

}
