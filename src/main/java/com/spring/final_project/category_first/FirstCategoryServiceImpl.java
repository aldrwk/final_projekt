package com.spring.final_project.category_first;

import com.spring.final_project.category_first.mapper.FirstCategoryReadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@CacheConfig(cacheNames = "layoutCaching")
public class FirstCategoryServiceImpl implements FirstCategoryService {

	FirstCategoryReadMapper firstCategoryReadMapper;

	@Autowired
	public FirstCategoryServiceImpl(FirstCategoryReadMapper firstCategoryReadMapper) {
		this.firstCategoryReadMapper = firstCategoryReadMapper;
	}

	@Override
	@Transactional(readOnly = true)
	public int findByName(String firstCateName) {
		return firstCategoryReadMapper.findByName(firstCateName);
	}

	@Override
	@Cacheable(key = "'allFirstCategory'")
	@Transactional(readOnly = true)
	public List<FirstCategoryDomain> findAll() {
		return firstCategoryReadMapper.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public String findCategoryName(int productNum) {
		return firstCategoryReadMapper.findCategoryName(productNum);
	}
}

