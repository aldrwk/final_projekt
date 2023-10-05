package com.spring.final_project.host;

public interface hostService {

	public int insert(hostDomain host);

	public hostDomain findByEmail(String email);
}
