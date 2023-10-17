package com.spring.final_project.category_second;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecondCategoryServiceImpl implements SecondCategoryService {

	SecondCategoryMapper secondCategoryMapper;

	public SecondCategoryServiceImpl(SecondCategoryMapper secondCategoryMapper) {
		this.secondCategoryMapper = secondCategoryMapper;
	}

	@Override
	public SecondCategoryDomain findById(int id) {
		return secondCategoryMapper.findById(id);
	}

	@Override
	public List<SecondCategoryDomain> findAll() {
		return secondCategoryMapper.findAll();
	}
	@Override
	public List<SecondCategoryDomain> findByFirstCategory(int num) {
		return secondCategoryMapper.findByFirstCategory(num);
	}

}
