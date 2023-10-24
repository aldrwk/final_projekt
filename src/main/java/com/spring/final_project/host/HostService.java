package com.spring.final_project.host;

public interface HostService {

	public int insert(HostDomain host);

	public HostDomain findById(String email);

	public HostDomain findByHostNum(int hostNum);
	public int updateInfo(HostDomain host);

}
