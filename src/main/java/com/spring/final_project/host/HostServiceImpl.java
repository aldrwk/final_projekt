package com.spring.final_project.host;

import com.spring.final_project.host.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HostServiceImpl implements HostService {

	private HostWriteMapper hostWriteMapper;
	private HostReadMapper hostReadMapper;

	@Autowired
	public HostServiceImpl(HostWriteMapper hostWriteMapper, HostReadMapper hostReadMapper ) {
		this.hostWriteMapper = hostWriteMapper;
		this.hostReadMapper =  hostReadMapper;
	}

	@Override
	@Transactional
	public int insert(HostDomain host) {
		return hostWriteMapper.insert(host);
	}

	@Override
	@Transactional(readOnly = true)
	public HostDomain findById(String email) {
		return hostReadMapper.findById(email);
	}

	@Override
	@Transactional(readOnly = true)
	public HostDomain findByHostNum(int hostNum) {
		return hostReadMapper.findByHostNum(hostNum);
	}

	@Override
	@Transactional
	public int updateInfo(HostDomain host) {
		return hostWriteMapper.updateInfo(host);
	}
}
