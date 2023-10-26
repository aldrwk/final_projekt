package com.spring.final_project.category_first;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class FirstCategoryServiceImpl implements FirstCategoryService {

	FirstCategoryMapper firstCategoryMapper;

	@Autowired
	public FirstCategoryServiceImpl(FirstCategoryMapper firstCategoryMapper) {
		this.firstCategoryMapper = firstCategoryMapper;
	}

	@Override
	@Transactional
	public int findByName(String firstCateName) {
		return firstCategoryMapper.findByName(firstCateName);
	}

	@Override
	@Transactional
	public List<FirstCategoryDomain> findAll() {
		return firstCategoryMapper.findAll();
	}

	@Override
	public String findCategoryName(int productNum) {
		return firstCategoryMapper.findCategoryName(productNum);
	}
}

