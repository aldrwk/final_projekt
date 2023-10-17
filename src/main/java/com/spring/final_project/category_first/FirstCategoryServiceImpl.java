package com.spring.final_project.category_first;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FirstCategoryServiceImpl implements FirstCategoryService {

	FirstCategoryMapper firstCategoryMapper;

	public FirstCategoryServiceImpl(FirstCategoryMapper firstCategoryMapper) {
		this.firstCategoryMapper = firstCategoryMapper;
	}

	@Override
	public FirstCategoryDomain findByfindByName(String name) {
		return firstCategoryMapper.findByName(name);
	}

	@Override
	public List<FirstCategoryDomain> findAll() {
		return firstCategoryMapper.findAll();
	}
}
