package com.spring.final_project.host;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class hostServiceImpl implements hostService{

	private hostMapper hostMapper;
	@Autowired
	public hostServiceImpl(hostMapper hostMapper) {
		this.hostMapper = hostMapper;
	}

	@Override
	public int insert(hostDomain host) {
		return hostMapper.insert(host);
	}

	@Override
	public hostDomain findById(String email) {
		return hostMapper.findById(email);
	}

	@Override
	public int updateInfo(hostDomain host) {
		return hostMapper.updateInfo(host);
	}
}
