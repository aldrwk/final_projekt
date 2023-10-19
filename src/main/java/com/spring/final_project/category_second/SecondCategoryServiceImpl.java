package com.spring.final_project.category_second;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class SecondCategoryServiceImpl implements SecondCategoryService {

	SecondCategoryMapper secondCategoryMapper;

	@Autowired
	public SecondCategoryServiceImpl(SecondCategoryMapper secondCategoryMapper) {
		this.secondCategoryMapper = secondCategoryMapper;
	}

	@Override
	@Transactional
	public SecondCategoryDomain findById(int id) {
		return secondCategoryMapper.findById(id);
	}

	@Override
	@Transactional
	public List<SecondCategoryDomain> findAll() {
		return secondCategoryMapper.findAll();
	}
	@Override
	@Transactional
	public List<SecondCategoryDomain> findByFirstCategory(int num) {
		return secondCategoryMapper.findByFirstCategory(num);
	}

	@Override
	@Transactional
	public int findByName(Map<String, Object> data) {
		return secondCategoryMapper.findByName(data);
	}

}
