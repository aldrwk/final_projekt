package com.spring.final_project.host;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class hostServiceImpl implements hostService{

	private hostMapper hostMapper;
	@Autowired
	public hostServiceImpl(hostMapper hostMapper) {
		this.hostMapper = hostMapper;
	}

	@Override
	@Transactional
	public int insert(hostDomain host) {
		return hostMapper.insert(host);
	}

	@Override
	@Transactional
	public hostDomain findById(String email) {
		return hostMapper.findById(email);
	}

	@Override
	public hostDomain findByHostNum(int hostNum) {
		return hostMapper.findByHostNum(hostNum);
	}

	@Override
	@Transactional
	public int updateInfo(hostDomain host) {
		return hostMapper.updateInfo(host);
	}
}
