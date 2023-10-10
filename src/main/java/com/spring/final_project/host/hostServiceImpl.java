package com.spring.final_project.host;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class hostServiceImpl implements hostService{

	hostMapper hostMapper;
	@Autowired
	public hostServiceImpl(hostMapper hostMapper) {
		this.hostMapper = hostMapper;
	}

	@Override
	public int insert(hostDomain host) {
		return hostMapper.insert(host);
	}


}
