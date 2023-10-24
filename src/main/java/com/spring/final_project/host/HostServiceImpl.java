package com.spring.final_project.host;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HostServiceImpl implements HostService {

	private HostMapper hostMapper;
	@Autowired
	public HostServiceImpl(HostMapper hostMapper) {
		this.hostMapper = hostMapper;
	}

	@Override
	@Transactional
	public int insert(HostDomain host) {
		return hostMapper.insert(host);
	}

	@Override
	@Transactional
	public HostDomain findById(String email) {
		return hostMapper.findById(email);
	}

	@Override
	public HostDomain findByHostNum(int hostNum) {
		return hostMapper.findByHostNum(hostNum);
	}

	@Override
	@Transactional
	public int updateInfo(HostDomain host) {
		return hostMapper.updateInfo(host);
	}
}
